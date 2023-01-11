package com.dh.PIG11.service;


import com.dh.PIG11.dto.ImageTransferDTO;
import com.sun.xml.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Qualifier("ImageTransferServiceImpl")
@Service
public class ImageTransferServiceImpl implements IImageTransferService {
    @Value("${image.bucket.name}")
    private String BUCKET_NAME;

    //TODO.DUPLICAR EL METODO
    @Override
    public void transferImage(ImageTransferDTO imageTransferDTO) throws IOException {


        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(
                "AKIASDYGBXDRSXUMPP5Q",
                "10raTa+L8gnvOZrby4dCsqi13wviG2eltD03RcBg");

        Region region = Region.US_EAST_2;
        S3Client client = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(region).build();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(imageTransferDTO.getPathFile())
                .build();
        client.putObject(request,
                RequestBody.fromInputStream(imageTransferDTO.getMultipart().getInputStream(), imageTransferDTO.getMultipart().getInputStream().available()));

    }

}

