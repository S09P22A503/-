package com.sshmarket.article.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshmarket.article.application.feignclient.MemberFeignClient;
import com.sshmarket.article.application.feignclient.ReviewFeignClient;
import com.sshmarket.article.application.repository.ArticleBookmarkRepository;
import com.sshmarket.article.application.repository.ArticleRepository;
import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.ArticleImage;
import com.sshmarket.article.domain.TradeType;
import com.sshmarket.article.dto.ArticleCardResponseDto;
import com.sshmarket.article.dto.ArticleDetailResponseDto;
import com.sshmarket.article.dto.Member;
import com.sshmarket.article.dto.ReviewResponseDto;
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


//        Member member = Member.createWithPublicInfo(1L, "보숙", "https://images.chosun.com/resizer/iNZAEGcD0GFzHTGZrdNQpeiwTIw=/530x718/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/6NARDQ2I2QN3J3ZDAC4S6W5DHE.jpg");

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
                .build();

        article.addViewCount();
        articleRepository.saveArticle(article);

        return responseDto;
    }

    @Transactional(readOnly = true)
    public Page<ArticleCardResponseDto> findArticleList(Integer category, Long locationId, TradeType tradeType, String keyword, Pageable pageable){

        List<Article> articleList = articleRepository.findArticleListByKeyword(category, locationId, tradeType, keyword, pageable);

        // articleId의 리스트 추출
        List<Long> articleIds = articleList.stream()
                .map(Article::getId)
                .collect(Collectors.toList());

        Map<Long, Article> articleMap = articleList.stream()
                .collect(Collectors.toMap(Article::getId, Function.identity()));

        // articleId에 맞는 리뷰 정보 가져오기
        List<ReviewResponseDto> reviews = reviewFeignClient.getMemberList(articleIds);
        Map<Long, ReviewResponseDto> reviewMap = reviews.stream()
                .collect(Collectors.toMap(ReviewResponseDto::getArticleId, Function.identity()));


        // articleId에 맞게 맵핑하여 dto 생성
        List<ArticleCardResponseDto> result = new ArrayList<>();
        for (Long articleId : articleIds) {
            Article article = articleMap.get(articleId);
            ReviewResponseDto reviewResponseDto = reviewMap.get(articleId);

            result.add(ArticleCardResponseDto.builder()
                            .id(articleId)
                            .amount(article.getAmount())
                            .mass(article.getMass())
                            .mainImageUrl(article.getMainImage())
                            .title(article.getTitle())
                            .price(article.getPrice())
                            .reviewCnt(reviewResponseDto.getReviewCnt())
                            .starRating(reviewResponseDto.getStarRating())
                        .build());
        }

        return new PageImpl<>(result, pageable, result.size());
    }
}
