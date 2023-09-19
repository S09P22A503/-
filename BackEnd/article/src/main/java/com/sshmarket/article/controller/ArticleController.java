package com.sshmarket.article.controller;


import com.sshmarket.article.application.AddArticleUseCase;
import com.sshmarket.article.dto.ArticleAddRequestDto;
import com.sshmarket.article.global.dto.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final AddArticleUseCase addArticleUseCase;

    @PostMapping
    public ResponseEntity<?> articleAdd(@Valid ArticleAddRequestDto articleAddRequestDto){
        return HttpResponse.okWithData(HttpStatus.CREATED, "글이 생성 완료되었습니다.",
                addArticleUseCase.addArticle(articleAddRequestDto)) ;
    }
}
