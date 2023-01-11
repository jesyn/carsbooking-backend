package com.dh.PIG11.service;

import com.dh.PIG11.dto.ImageDTO;
import com.dh.PIG11.entities.Image;
import com.dh.PIG11.entities.Product;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.repository.ImageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Qualifier("ImageServiceImpl")
@Service
public class ImageServiceImpl implements IImageService{
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    ObjectMapper objectMapper;



    @Override
    public ImageDTO agregarImagen(ImageDTO image, Product product) {

        Image newImage= objectMapper.convertValue(image, Image.class);
        newImage.setProduct(product);
        return objectMapper.convertValue(imageRepository.save(newImage), ImageDTO.class);
    }

    @Override
    public List<ImageDTO> listarImagenes() {

        List<Image> allImages = imageRepository.findAll();
        List<ImageDTO> allImagesDTO = new ArrayList<>();
        for(Image image: allImages)
            allImagesDTO.add(objectMapper.convertValue(image,ImageDTO.class));

        return allImagesDTO;
    }

    @Override
    public ImageDTO buscarImagen(Long id) throws ResourceNotFoundException{
        Optional<Image> imagenBuscada=imageRepository.findById(id);
        if (imagenBuscada.isPresent()){
            log.info("Comenzo el proceso: buscando imagen");
            return objectMapper.convertValue(imagenBuscada, ImageDTO.class);
        }
        else {
            throw new ResourceNotFoundException("No se encontro la imagen con id= "+id+". Error al ingresar el id.");

        }
    }

    @Override
    public ResponseEntity<Image> editarImagen(ImageDTO image) {
        Image editImage=  objectMapper.convertValue(image, Image.class);
        return ResponseEntity.ok(imageRepository.save(editImage));
    }


    @Override
    public void eliminarImagen(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public List<ImageDTO> agregarImagenes(List<ImageDTO> images, Product product) {

        List<ImageDTO> allImagesDTO = new ArrayList<>();

        for(ImageDTO image: images){
            allImagesDTO.add(agregarImagen(image, product));
        }


        return allImagesDTO;
    }
}
