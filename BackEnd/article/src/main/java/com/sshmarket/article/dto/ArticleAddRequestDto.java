package com.sshmarket.article.dto;

import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.Location;
import com.sshmarket.article.domain.Product;
import com.sshmarket.article.domain.TradeType;
import com.sshmarket.article.dto.validate.AllowedContentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class ArticleAddRequestDto {

    private Long memberId;

    @NotNull(message = "상품 id가 비었습니다.")
    private Integer itemId;

    @NotNull(message = "가격을 입력해주세요.")
    private Integer price;

    private Integer amount;

    private Integer mass;

    private Long locationId;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @NotNull(message = "거래방식을 선택해주세요.")
    private TradeType tradeType;

    @AllowedContentType(allowedTypes = {"image/jpg", "image/jpeg", "image/png"},
            allowedExtensions = {"jpg", "jpeg", "png"})
    @NotNull(message = "대표 이미지를 선택해주세요.")
    private MultipartFile mainImage;

    private List<MultipartFile> images;

    public Article toEntity(String mainImageUrl, List<String> imageUrls, Location location, Product product) {
        return Article.createArticle(memberId, product, location, price,
                amount, mass, tradeType, title, content, mainImageUrl, imageUrls);
    }

}
