package com.dh.PIG11.utils;

import com.dh.PIG11.dto.ImageTransferDTO;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ImageUtils {
    @Value("${image.server.url}")
    private String IMAGE_URL;
    private final String FOLDER_PRODUCTS="products";
    public ImageTransferDTO convertToImageTransferForPortada(MultipartFile file){
        return convertToImageTransfer(file, FOLDER_PRODUCTS);

    }
    private final String FOLDER_IMAGES="images";
    public ImageTransferDTO convertToImageTransferForImage(MultipartFile file){
        return convertToImageTransfer(file, FOLDER_IMAGES);




    }
    private ImageTransferDTO convertToImageTransfer(MultipartFile file, String folder){
        UUID uuid = UUID.randomUUID();
        String extension= FilenameUtils.getExtension(file.getOriginalFilename());

        return ImageTransferDTO.builder()
                .multipart(file)
                .url(String.format("%s/%s/%s.%s",IMAGE_URL,folder,uuid.toString(),extension))
                .keyName(String.format("%s.%s",uuid.toString(),extension))
                .pathFile(String.format("%s/%s.%s",folder,uuid.toString(),extension))
                .build();


    }
}
