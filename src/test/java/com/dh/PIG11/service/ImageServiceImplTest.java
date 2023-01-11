/*
package com.dh.PIG11.service;

import com.dh.PIG11.entities.Characteristic;
import com.dh.PIG11.entities.Image;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImageServiceImplTest {
    @Autowired
    private ImageServiceImpl imageService;
    @Test
    @Order(1)
    public void agregarImagen() {
        Image image=new Image(20L,"pruebo","auto");
        assertNotNull(imageService.agregarImagen(image));
        imageService.eliminarImagen(image.getId());
    }

    @Test
    @Order(2)
    public void listarImagenes() {
        Image image=imageService.agregarImagen(new Image(20L,"pruebo","auto"));
        assertNotNull(imageService.listarImagenes());
        imageService.eliminarImagen(image.getId());
    }

    @Test
    @Order(3)
    public void buscaryEliminarImagen() {
        Image image=imageService.agregarImagen(new Image(20L,"pruebo","auto"));
        imageService.eliminarImagen(image.getId());
        Optional<Image> imagenBuscada=imageService.buscarImagen(image.getId());
        //assertTrue(imagenBuscada.isEmpty());
    }




}
*/