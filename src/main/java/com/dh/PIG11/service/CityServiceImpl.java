package com.dh.PIG11.service;

import com.dh.PIG11.dto.CityDTO;
import com.dh.PIG11.entities.City;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.repository.CityRepository;
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
@Qualifier("CityServiceImpl")
@Service
public class CityServiceImpl implements ICityService{
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private CityRepository cityRepository;



    @Override
    public ResponseEntity<City> agregarCiudad(CityDTO city) {
        City newCity= objectMapper.convertValue(city,City.class);
        return ResponseEntity.ok(cityRepository.save(newCity));
    }

    @Override
    public List<CityDTO> listarCiudades() {
        List<City> allCities = cityRepository.findAll();
        List<CityDTO> allCitiesDTO = new ArrayList<>();
        for(City city: allCities)
            allCitiesDTO.add(objectMapper.convertValue(city,CityDTO.class));

        return allCitiesDTO;
    }

    @Override
    public CityDTO buscarCiudad(Long id) throws ResourceNotFoundException{
        Optional<City> ciudadBuscada=cityRepository.findById(id);
        if (ciudadBuscada.isPresent()){
            log.info("Comenzo el proceso: buscando ciudad");
            return objectMapper.convertValue(ciudadBuscada,CityDTO.class);
        }
        else {
            throw new ResourceNotFoundException("No se encontro la ciudad con id= "+id+". Error al ingresar el id.");

        }
    }

    @Override
    public void eliminarCiudad(Long id) {
        cityRepository.deleteById(id);
    }
}
