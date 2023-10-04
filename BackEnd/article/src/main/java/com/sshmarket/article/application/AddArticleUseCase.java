package com.sshmarket.article.application;

import com.sshmarket.article.application.repository.ArticleImageRepository;
import com.sshmarket.article.application.repository.ArticleRepository;
import com.sshmarket.article.application.repository.LocationRepository;
import com.sshmarket.article.application.repository.ProductRepository;
import com.sshmarket.article.domain.Article;
import com.sshmarket.article.domain.ArticleImage;
import com.sshmarket.article.domain.Location;
import com.sshmarket.article.domain.Product;
import com.sshmarket.article.dto.ArticleAddRequestDto;
import com.sshmarket.article.global.exception.NotFoundResourceException;
import com.sshmarket.article.global.support.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
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
public class AddArticleUseCase {

    private String imageDir = "https://a503.s3.ap-northeast-2.amazonaws.com/";

    private final ArticleRepository articleRepository;
    private final LocationRepository locationRepository;
    private final ProductRepository productRepository;
    private final ArticleImageRepository articleImageRepository;
    private final S3Uploader s3Uploader;

    public Long addArticle(ArticleAddRequestDto articleAddRequestDto){

        Location location = locationRepository.findLocationById(articleAddRequestDto.getLocationId())
                .orElseThrow(()-> new NotFoundResourceException("존재하지 않는 지역입니다."));

        System.out.println(articleAddRequestDto.getItemId());
        List<Product> productList = productRepository.findProductByItemId(articleAddRequestDto.getItemId());
        Product product = null;
        if(productList.size()==0) throw new NotFoundResourceException("존재하지 않는 상품입니다.");
        else product = productList.get(0);

        String directory = "article/image/";
        String mainImageName = makeFileName(directory, articleAddRequestDto.getMainImage());
        List<String> imageFileNames = new ArrayList<>();
        List<ArticleImage> images = new ArrayList<>();

        uploadList(directory, articleAddRequestDto.getImages(), imageFileNames, images);

        Article article = articleAddRequestDto.toEntity(imageDir + mainImageName, imageFileNames, location, product);
        Long id = articleRepository.saveArticle(article).getId();

        articleImageRepository.saveImages(article.getArticleImages());


        s3Uploader.upload(mainImageName, articleAddRequestDto.getMainImage());

        return id;
    }

    private void uploadList(String directory, List<MultipartFile> images, List<String> imageFileNames, List<ArticleImage> imageList){
        String fileName = "";

        for (MultipartFile file : images) {
            fileName = makeFileName(directory, file);
            s3Uploader.upload(fileName, file);
            imageFileNames.add(imageDir + fileName);
        }
    }

    private String makeFileName(String directory, MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }
        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        return directory + UUID.randomUUID() + "." + extension;
    }
}
