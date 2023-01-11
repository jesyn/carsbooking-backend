package com.dh.PIG11.controller;

import com.dh.PIG11.dto.ImageDTO;
import com.dh.PIG11.entities.Image;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.service.IImageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/images")
@CrossOrigin
public class ImageController {

    private static final Logger logger= Logger.getLogger(IImageService.class);
    @Autowired
    private IImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> buscarImagenesXId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(imageService.buscarImagen(id));}

    @GetMapping
    public ResponseEntity<List<ImageDTO>> listaImagenes(){
        logger.info("Comenzo el proceso: listando imagenes");
        return ResponseEntity.ok(imageService.listarImagenes());
    }

    @PostMapping
    public ResponseEntity<?> registrarNuevaImagen(@RequestBody ImageDTO image){
        logger.info("Comenzo el proceso: guardando imagen");
        return ResponseEntity.ok(imageService.agregarImagen(image, null));
    }

    @PutMapping
    public ResponseEntity<?> editarImagen(@RequestBody ImageDTO image) throws ResourceNotFoundException {
        Optional<ImageDTO> imagenBuscada= Optional.ofNullable(imageService.buscarImagen(image.getId()));
        if (imagenBuscada.isPresent()){
            logger.info("Se edito la imagen");
            imageService.editarImagen(image);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        else {
            throw new ResourceNotFoundException("No se encontro la imagen con id= "+ image.getId()+" para editarla. Error al ingresar el id.");

        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarImagen(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<ImageDTO> imagenBuscada= Optional.ofNullable(imageService.buscarImagen(id));
        if(imagenBuscada.isPresent()){
            imageService.eliminarImagen(id);
            return ResponseEntity.ok("Se elimino la imagen con id= "+ id);
        }
        else{
            throw new ResourceNotFoundException("No se encontro la imagen con id= "+id+" para eliminarla. Error al ingresar el id.");

        }

    }


}

