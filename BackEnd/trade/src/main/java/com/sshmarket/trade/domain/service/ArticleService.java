package com.sshmarket.trade.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sshmarket.trade.application.ArticleClient;
import com.sshmarket.trade.dto.ArticleDetailResponseDto;
import com.sshmarket.trade.dto.TradeDetailResponseDto;
import com.sshmarket.trade.exception.NotFoundResourceException;
import com.sshmarket.trade.infra.jwt.JwtTranslator;
import java.util.LinkedHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleClient articleClient;

    public ArticleDetailResponseDto getArticleResponse(Long articleId) {
        ObjectMapper objectMapper = new ObjectMapper();
        Object articleResponse = ((LinkedHashMap) articleClient.articleDetail(articleId)
                                                               .getBody()).get("data");
<<<<<<< HEAD
        ArticleDetailResponseDto from = ArticleDetailResponseDto.from(
                (LinkedHashMap) articleResponse);
        return from;
=======
        try {
            return objectMapper.readValue(
                    objectMapper.writeValueAsString(articleResponse),
                    ArticleDetailResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new NotFoundResourceException("해당 상품이 존재하지 않습니다.");
        }
>>>>>>> d055418397d27c40756c4cac869d4f8c85f3a7c8
    }
}
