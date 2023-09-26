package com.sshmarket.auth.auth.adapter.out.persistent.aws;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sshmarket.auth.auth.adapter.out.persistent.aws.exception.S3UploadException;
import com.sshmarket.auth.auth.application.port.out.MemberProfileRepository;
import com.sshmarket.auth.auth.domain.MemberProfile;
import com.sshmarket.auth.auth.exception.BusinessException;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class S3Repository implements MemberProfileRepository {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.directory}")
    private String directory;

    @Override
    public MemberProfile saveMemberProfile(MemberProfile memberProfile) {

        MultipartFile file = memberProfile.getFile();
        String path = directory + memberProfile.getName();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try {
            amazonS3Client.putObject(
                    new PutObjectRequest(bucket, path, file.getInputStream(),
                            objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
            );
        } catch (IOException e) {
            throw new S3UploadException("프로필 사진이 저장되지 않았습니다.");
        }

        String url = amazonS3Client.getUrl(bucket, path)
                                   .toString();
        memberProfile.fillMemberProfileUrl(url);
        return memberProfile;
    }

    @Override
    public MemberProfile removeMemberProfile(MemberProfile memberProfile) {

        String path = directory + memberProfile.getName();
        System.out.println(path);

        if (amazonS3Client.doesObjectExist(bucket, path)) {
            System.out.println(path);
            amazonS3Client.deleteObject(bucket, path);
        } else {
            throw new S3UploadException("삭제할 프로필 사진이 없습니다.");
        }

        memberProfile.fillMemberProfileUrl(null);

        return memberProfile;
    }
}