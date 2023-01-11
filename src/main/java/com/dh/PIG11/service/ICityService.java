package com.dh.PIG11.service;

import com.dh.PIG11.dto.CityDTO;
import com.dh.PIG11.entities.City;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICityService {
    ResponseEntity<City> agregarCiudad(CityDTO city);
    List<CityDTO> listarCiudades();
    CityDTO buscarCiudad(Long id);
    void eliminarCiudad (Long id);
}
