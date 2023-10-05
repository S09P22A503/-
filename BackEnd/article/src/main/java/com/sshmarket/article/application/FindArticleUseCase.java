package com.sshmarket.article.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshmarket.article.application.feignclient.MemberFeignClient;
import com.sshmarket.article.application.feignclient.ReviewFeignClient;
import com.sshmarket.article.application.repository.ArticleBookmarkRepository;
import com.sshmarket.article.application.repository.ArticleRepository;
import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.ArticleBookmark;
import com.sshmarket.article.domain.ArticleImage;
import com.sshmarket.article.domain.TradeType;
import com.sshmarket.article.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class  FindArticleUseCase {
// 디테일, 리스트, 정렬 기준 검색,

    private final ArticleRepository articleRepository;
    private final ArticleBookmarkRepository articleBookmarkRepository;


    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MemberFeignClient memberFeignClient;
    private final ReviewFeignClient reviewFeignClient;

    // 글 상세 조회
    @Transactional
    public ArticleDetailResponseDto findArticleDetail(Long articleId, Long memberId){
        Article article = articleRepository.findArticleDetailById(articleId);

        Boolean isLike = articleBookmarkRepository.isExistArticleBookmark(article, memberId);

        Object memberResponse = ((LinkedHashMap) memberFeignClient.getMember(article.getMemberId())
                .getBody()).get("data");

        Member member = null;
        try {
            member = objectMapper.readValue(
                    objectMapper.writeValueAsString(memberResponse), Member.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ArticleDetailResponseDto responseDto = ArticleDetailResponseDto.builder()
                .id(articleId)
                .title(article.getTitle())
                .amount(article.getAmount())
                .mass(article.getMass())
                .mainImage(article.getMainImage())
                .images(article.getArticleImages().stream()
                        .map(ArticleImage::getImageUrl)
                        .collect(Collectors.toList()))
                .itemId(article.getProduct().getItemId())
                .content(article.getContent())
                .location(article.getLocation().getLocationName())
                .isLike(isLike)
                .price(article.getPrice())
                .member(member)
                .tradeType(article.getTradeType())
                .build();


        return responseDto;
    }

    // 글 검색
    @Transactional(readOnly = true)
    public Page<ArticleCardResponseDto> findArticleList(Integer category, Long locationId, TradeType tradeType, String keyword, Pageable pageable){

        Page<Article> articleList = articleRepository.findArticleListByKeyword(category, locationId, tradeType, keyword, pageable);

        List<ArticleCardResponseDto> result = articleListByArticles(articleList.getContent());

        return new PageImpl<>(result, pageable, articleList.getTotalElements());
    }

    // 글 id 리스트를 받아 id와 제목 리스트 반환
    @Transactional(readOnly = true)
    public List<ArticleTitleResponseDto> findArticleTitleByArticleIds(List<Long> articleIds){
        List<Article> articles = articleRepository.findArticleByArticleIds(articleIds);
        List<ArticleTitleResponseDto> responseDto = new ArrayList<>();

        for (Article article : articles) {
            responseDto.add(new ArticleTitleResponseDto(article.getId(), article.getTitle()));
        }

        return responseDto;
    }

    // 특정 사용자가 작성한 글 리스트
    @Transactional(readOnly = true)
    public Page<ArticleCardResponseDto> memberArticleList(Long memberId, Pageable pageable){
        Page<Article> articles = articleRepository.findArticleListByMember(memberId, pageable);

        List<ArticleCardResponseDto> result = articleListByArticles(articles.getContent());

        return new PageImpl<>(result, pageable, articles.getTotalElements());
    }

    // 좋아요한 글 리스트 반환
    @Transactional(readOnly = true)
    public Page<ArticleCardResponseDto> bookmarkArticleList(Long memberId, Pageable pageable){
        Page<ArticleBookmark> articleBookmarks = articleBookmarkRepository.findArticleBookmarkByMemberId(memberId, pageable);
        List<Article> articleList = articleBookmarks.getContent().stream()
                .map(ArticleBookmark::getArticle)
                .collect(Collectors.toList());

        List<ArticleCardResponseDto> result = articleListByArticles(articleList);

        return new PageImpl<>(result, pageable, articleBookmarks.getTotalElements());
    }

    //Article의 리스트를 카드 dto로 변환
    private List<ArticleCardResponseDto> articleListByArticles(List<Article> articleList){
        // articleId의 리스트 추출
        List<Long> articleIds = articleList.stream()
                .map(Article::getId)
                .collect(Collectors.toList());

        Map<Long, Article> articleMap = articleList.stream()
                .collect(Collectors.toMap(Article::getId, Function.identity()));

        // articleId에 맞는 리뷰 정보 가져오기
        List<ReviewRatingAndNum> reviews = reviewFeignClient.getReviewList(articleIds);

        Map<Long, ReviewRatingAndNum> reviewMap = reviews.stream()
                .collect(Collectors.toMap(ReviewRatingAndNum::getArticleId, Function.identity()));


        // articleId에 맞게 맵핑하여 dto 생성
        List<ArticleCardResponseDto> result = new ArrayList<>();
        for (Long articleId : articleIds) {
            Article article = articleMap.get(articleId);
            ReviewRatingAndNum reviewResponseDto = reviewMap.get(articleId);

            result.add(ArticleCardResponseDto.builder()
                    .id(articleId)
                    .amount(article.getAmount())
                    .mass(article.getMass())
                    .mainImageUrl(article.getMainImage())
                    .title(article.getTitle())
                    .price(article.getPrice())
                    .reviewCnt(reviewResponseDto!=null ? reviewResponseDto.getReviewCnt() : 0)
                    .starRating(reviewResponseDto!=null ? reviewResponseDto.getStarRating() : 0)
                    .build());
        }
        return result;
    }

    public List<ArticleCardResponseDto> articleListByArticleIdList(List<Long> articleIds){
        List<Article> articles = articleRepository.findArticleByArticleIds(articleIds);

        return articleListByArticles(articles);
    }
}
