package com.sshmarket.article.controller;


import com.sshmarket.article.application.*;
import com.sshmarket.article.application.jwttranslator.JwtTranslator;
import com.sshmarket.article.domain.TradeType;
import com.sshmarket.article.dto.ArticleAddRequestDto;
import com.sshmarket.article.dto.ArticleModifyRequestDto;
import com.sshmarket.article.dto.ArticleTitleResponseDto;
import com.sshmarket.article.dto.Member;
import com.sshmarket.article.global.dto.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final AddArticleUseCase addArticleUseCase;
    private final ModifyArticleUseCase modifyArticleUseCase;
    private final RemoveArticleUseCase removeArticleUseCase;
    private final ArticleBookmarkUseCase articleBookmarkUseCase;
    private final FindArticleUseCase findArticleUseCase;

    private final JwtTranslator jwtTranslator;


    // 글 생성
    @PostMapping
    public ResponseEntity<?> articleAdd(@ModelAttribute @Valid ArticleAddRequestDto articleAddRequestDto,
                                        @CookieValue(value = "jwt", required = true) String token){
        Member member = jwtTranslator.translate(token);

        articleAddRequestDto.setMemberId(member.getId());

        return HttpResponse.okWithData(HttpStatus.CREATED, "글이 생성 완료되었습니다.",
                addArticleUseCase.addArticle(articleAddRequestDto));
    }

    // 글 수정
    @PatchMapping("/{articleId}")
    public ResponseEntity<?> articleModify(@PathVariable Long articleId,
                                           @ModelAttribute @Valid ArticleModifyRequestDto articleModifyRequestDto,
                                           @CookieValue(value = "jwt", required = true) String token){

        Member member = jwtTranslator.translate(token);
        articleModifyRequestDto.setMemberId(member.getId());

        modifyArticleUseCase.modifyArticle(articleModifyRequestDto);

        return HttpResponse.okWithData(HttpStatus.OK, "글이 수정 완료되었습니다.", articleId);
    }

    // 글 삭제
    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> articleRemove(@PathVariable Long articleId,
                                           @CookieValue(value = "jwt", required = true) String token){
        Member member = jwtTranslator.translate(token);
        removeArticleUseCase.removeArticle(articleId, member.getId());

        return HttpResponse.ok(HttpStatus.OK, "글이 삭제 완료되었습니다.");
    }

    // 글 북마크 추가
    @PostMapping("/{articleId}/bookmarks")
    public ResponseEntity<?> articleBookmarkAdd(@PathVariable Long articleId,
                                                @CookieValue(value = "jwt", required = true) String token){
        Member member = jwtTranslator.translate(token);

        articleBookmarkUseCase.addArticleBookmark(articleId, member.getId());

        return HttpResponse.ok(HttpStatus.CREATED, "북마크 생성 완료");
    }

    // 북마크 삭제
    @DeleteMapping("/{articleId}/bookmarks")
    public ResponseEntity<?> articleBookmarkRemove(@PathVariable Long articleId,
                                                   @CookieValue(value = "jwt", required = true) String token){
        Member member = jwtTranslator.translate(token);

        articleBookmarkUseCase.removeArticleBookmark(articleId, member.getId());

        return HttpResponse.ok(HttpStatus.OK, "북마크 삭제 완료");
    }

    // 글 상세조회
    @GetMapping("/{articleId}")
    public ResponseEntity<?> articleDetail(@PathVariable Long articleId,
                                           @CookieValue(value = "jwt", required = false) String token){

        Member member = jwtTranslator.translate(token);

        return HttpResponse.okWithData(HttpStatus.OK, "판매글 상세 조회 성공",
                findArticleUseCase.findArticleDetail(articleId,
                        member == null ? null : member.getId()));
    }

    // 글 리스트 조회
    @GetMapping
    public ResponseEntity<?> articleList(@RequestParam(value = "category", required = false) Integer category,
                                         @RequestParam(value = "LocationId", required = false) Long LocationId,
                                         @RequestParam(value = "tradeType", required = false) TradeType tradeType,
                                         @RequestParam(value = "keyword", required = false) String keyword,
                                         Pageable pageable){
        return HttpResponse.okWithData(HttpStatus.OK, "판매글 리스트 조회 성공",
                findArticleUseCase.findArticleList(category, LocationId, tradeType, keyword, pageable));
    }

    @GetMapping("/articleTitle")
    public List<ArticleTitleResponseDto> articleTitleList(@RequestParam List<Long> articleIds){

        return findArticleUseCase.findArticleTitleByArticleIds(articleIds);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> memberArticleList(@PathVariable Long memberId, Pageable pageable){

        return HttpResponse.okWithData(HttpStatus.OK, "멤버 작성 판매글 리스트 조회 성공",
                findArticleUseCase.memberArticleList(memberId, pageable));
    }

    @GetMapping("/bookmark/{memberId}")
    public ResponseEntity<?> bookmarkArticleList(@PathVariable Long memberId, Pageable pageable){

        return HttpResponse.okWithData(HttpStatus.OK, "북마크한 판매글 리스트 조회 성공",
                findArticleUseCase.bookmarkArticleList(memberId, pageable));
    }

    @GetMapping("/recommend")
    public ResponseEntity<?> articleListByArticleIdList(@RequestParam List<Long> articleId) {

        return HttpResponse.okWithData(HttpStatus.OK, "추천 글 리스트 조회 성공",
                findArticleUseCase.articleListByArticleIdList(articleId));
    }
}
