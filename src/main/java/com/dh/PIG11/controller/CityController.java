package com.dh.PIG11.controller;

import com.dh.PIG11.dto.CityDTO;
import com.dh.PIG11.entities.City;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.service.ICityService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
@CrossOrigin
public class CityController {
    private static final Logger logger= Logger.getLogger(ICityService.class);
    @Autowired
    private ICityService cityService;
    @GetMapping
    public ResponseEntity<List<CityDTO>> listarCiudades(){
        logger.info("Comenzo el proceso: listando ciudades");
        return ResponseEntity.ok(cityService.listarCiudades());
    }
    @PostMapping
    public ResponseEntity<?> registrarNuevaCiudad(@RequestBody CityDTO city){
        logger.info("Comenzo el proceso: guardando ciudad");
        cityService.agregarCiudad(city);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> buscarCityXid(@PathVariable("id") Long id) {

            return ResponseEntity.ok(cityService.buscarCiudad(id));
       }
        @DeleteMapping("/{id}")
        public ResponseEntity<String> eliminarCiudad(@PathVariable("id") Long id) throws ResourceNotFoundException {
            Optional<CityDTO> ciudadBuscada= Optional.ofNullable(cityService.buscarCiudad(id));
            if(ciudadBuscada.isPresent()){
                cityService.eliminarCiudad(id);
                return ResponseEntity.ok("Se elimino la ciudad con id= "+ id);
            }
            else{
                throw new ResourceNotFoundException("No se encontro la ciudad con id= "+id+" para eliminarla. Error al ingresar el id.");

            }
}}
