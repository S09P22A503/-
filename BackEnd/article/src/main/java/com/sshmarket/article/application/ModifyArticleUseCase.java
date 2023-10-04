package com.sshmarket.article.application;

import com.sshmarket.article.application.repository.ArticleImageRepository;
import com.sshmarket.article.application.repository.ArticleRepository;
import com.sshmarket.article.application.repository.LocationRepository;
import com.sshmarket.article.application.repository.ProductRepository;
import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.ArticleImage;
import com.sshmarket.article.domain.Location;
import com.sshmarket.article.domain.Product;
import com.sshmarket.article.dto.ArticleModifyRequestDto;
import com.sshmarket.article.global.exception.NotFoundResourceException;
import com.sshmarket.article.global.exception.PermissionDeniedException;
import com.sshmarket.article.global.support.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ModifyArticleUseCase {

    private final ArticleRepository articleRepository;
    private final LocationRepository locationRepository;
    private final ProductRepository productRepository;
    private final ArticleImageRepository articleImageRepository;
    private final S3Uploader s3Uploader;

    private String imageDir = "https://a503.s3.ap-northeast-2.amazonaws.com/";
    private String directory = "article/image/";

    public void modifyArticle(ArticleModifyRequestDto articleModifyRequestDto){

        Article originalArticle = validateArticle(articleModifyRequestDto.getId());
        validateAuthor(originalArticle.getMemberId(), articleModifyRequestDto.getMemberId());

        Location location = locationRepository.findLocationById(articleModifyRequestDto.getLocationId())
                .orElseThrow(()-> new NotFoundResourceException("존재하지 않는 지역입니다."));

        List<Product> productList = productRepository.findProductByItemId(articleModifyRequestDto.getItemId());
        Product product = null;
        if(productList.size()==0) throw new NotFoundResourceException("존재하지 않는 상품입니다.");
        else product = productList.get(0);


        String mainImageName = "";

        if(articleModifyRequestDto.getMainImageChanged()) {
            // 메인 이미지가 바꼈으면 다시 저장
            s3Uploader.delete(originalArticle.getMainImage().replaceFirst(imageDir, ""));
            mainImageName = makeFileName(directory, articleModifyRequestDto.getMainImage());
        }
        else{
            mainImageName = originalArticle.getMainImage().replaceFirst(imageDir, "");
        }
        List<ArticleImage> imageFileNames = originalArticle.getArticleImages();
        removeImages(originalArticle, articleModifyRequestDto.getDeletedUrls(), imageFileNames);

        List<String> imageUrls = new ArrayList<>();

        uploadList(articleModifyRequestDto.getImages(), imageUrls);

        Article modifiedArticle = articleModifyRequestDto.toEntity(imageDir + mainImageName, imageUrls, location, product);

        originalArticle.modifyArticle(modifiedArticle);

        articleImageRepository.saveImages(modifiedArticle.getArticleImages());

        articleRepository.saveArticle(originalArticle);
    }

    // 지워진 파일들의 url 리스트를 받아서 삭제
    private void removeImages(Article article, List<String> deletedImages, List<ArticleImage> imageFileNames) {

        for (String imageUrl : deletedImages) {
            article.removeArticleImage(articleImageRepository.findImageByUrl(imageUrl)
                    .orElseThrow(() -> new NotFoundResourceException("존재하지 않는 이미지입니다.")));

            articleImageRepository.removeImageByImageUrl(imageUrl);
            s3Uploader.delete(imageUrl.replaceFirst(imageDir, ""));
        }
    }

    private void uploadList(List<MultipartFile> images, List<String> imageUrls){
        if(images.isEmpty()) return;

        String url = "";

        for (MultipartFile file : images) {
            url = makeFileName(directory, file);
            imageUrls.add(imageDir + url);
            s3Uploader.upload(url, file);
        }
    }

    private String makeFileName(String directory, MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }
        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        return directory + UUID.randomUUID() + "." + extension;
    }

    private void validateAuthor(Long originAuthor, Long author){
        if(originAuthor != author){
            throw new PermissionDeniedException("원글 작성자가 아닙니다.");
        }
    }

    private Article validateArticle(Long articleId){
        return articleRepository.findArticleById(articleId)
                .orElseThrow(() -> new NotFoundResourceException("존재하지 않는 글입니다."));
    }
}
