package com.dh.PIG11.service;

import com.dh.PIG11.dto.ImageTransferDTO;

import java.io.IOException;
import java.util.List;

public interface IImageTransferService {
    void transferImage(ImageTransferDTO imageTransferDTO) throws IOException;


}
