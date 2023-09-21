package com.sshmarket.article.controller;


import com.sshmarket.article.application.AddArticleUseCase;
import com.sshmarket.article.application.ModifyArticleUseCase;
import com.sshmarket.article.application.RemoveArticleUseCase;
import com.sshmarket.article.dto.ArticleAddRequestDto;
import com.sshmarket.article.dto.ArticleModifyRequestDto;
import com.sshmarket.article.global.dto.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final AddArticleUseCase addArticleUseCase;
    private final ModifyArticleUseCase modifyArticleUseCase;
    private final RemoveArticleUseCase removeArticleUseCase;

    @PostMapping
    public ResponseEntity<?> articleAdd(@ModelAttribute @Valid ArticleAddRequestDto articleAddRequestDto){
        return HttpResponse.okWithData(HttpStatus.CREATED, "글이 생성 완료되었습니다.",
                addArticleUseCase.addArticle(articleAddRequestDto));
    }

    @PatchMapping("/{articleId}")
    public ResponseEntity<?> articleModify(@PathVariable Long articleId,
                                           @ModelAttribute @Valid ArticleModifyRequestDto articleModifyRequestDto){

        modifyArticleUseCase.modifyArticle(articleModifyRequestDto);

        return HttpResponse.okWithData(HttpStatus.OK, "글이 수정 완료되었습니다.", articleId);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> articleRemove(@PathVariable Long articleId){
        removeArticleUseCase.removeArticle(articleId);

        return HttpResponse.ok(HttpStatus.OK, "글이 삭제 완료되었습니다.");
    }


}
