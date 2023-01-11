package com.dh.PIG11.service;


import com.dh.PIG11.dto.ImageDTO;
import com.dh.PIG11.entities.Image;
import com.dh.PIG11.entities.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IImageService {
    ImageDTO agregarImagen(ImageDTO image, Product product);
    List<ImageDTO> listarImagenes();
    ImageDTO buscarImagen(Long id);
    ResponseEntity<Image> editarImagen(ImageDTO image);
    void eliminarImagen (Long id);
    List<ImageDTO> agregarImagenes(List<ImageDTO> images, Product product);
}
