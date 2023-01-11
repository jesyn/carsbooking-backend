package com.dh.PIG11.controller;

import com.dh.PIG11.dto.CharacteristicDTO;
import com.dh.PIG11.entities.Characteristic;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.service.ICharacteristicService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/characteristics")
@CrossOrigin
public class CharacteristicController {
    private static final Logger logger= Logger.getLogger(ICharacteristicService.class);
    @Autowired
    private ICharacteristicService characteristicService;
    @PostMapping
    public ResponseEntity<?> registrarNuevaCaracteristica(@RequestBody CharacteristicDTO characteristic){
        logger.info("Comenzo el proceso: guardando caracteristica");
        characteristicService.agregarCaracteristica(characteristic);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CharacteristicDTO>> listarCaracteristicas(){
        logger.info("Comenzo el proceso: listando caracteristicas");
        return ResponseEntity.ok(characteristicService.listarCaracteristicas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacteristicDTO> buscarCharacteristicXid(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(characteristicService.buscarCaracteristica(id));}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCaracteristica(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<CharacteristicDTO> caracteristicaBuscada = Optional.ofNullable(characteristicService.buscarCaracteristica(id));
        if (caracteristicaBuscada.isPresent()) {
            characteristicService.eliminarCaracteristica(id);
            return ResponseEntity.ok("Se elimino la caracteristica con id= " + id);
        } else {
            throw new ResourceNotFoundException("No se encontro la caracteristica con id= " + id + " para eliminarla. Error al ingresar el id.");

        }

    }

}
