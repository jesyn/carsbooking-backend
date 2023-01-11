package com.dh.PIG11.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Builder
@Getter
@Setter
public class ImageTransferDTO {
    private String url;
    private MultipartFile multipart;
    private String keyName;
    private String pathFile;
}
