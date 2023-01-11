package com.dh.PIG11.service;

import com.dh.PIG11.dto.CharacteristicDTO;
import com.dh.PIG11.entities.Characteristic;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.repository.CharacteristicRepository;
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
@Qualifier("CharacteristicServiceImpl")
@Service
public class CharacteristicServiceImpl implements ICharacteristicService{
    @Autowired
    private CharacteristicRepository characteristicRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public CharacteristicDTO agregarCaracteristica(CharacteristicDTO characteristic) {
        Characteristic newCharacteristic=  objectMapper.convertValue(characteristic, Characteristic.class);
        return objectMapper.convertValue(characteristicRepository.save(newCharacteristic), CharacteristicDTO.class);

    }

    @Override
    public List<CharacteristicDTO> listarCaracteristicas() {
        List<Characteristic> allCharacteristics = characteristicRepository.findAll();
        List<CharacteristicDTO> allCharacteristicsDTO = new ArrayList<>();
        for(Characteristic characteristic: allCharacteristics)
            allCharacteristicsDTO.add(objectMapper.convertValue(characteristic,CharacteristicDTO.class));

        return allCharacteristicsDTO;
    }

    @Override
    public CharacteristicDTO buscarCaracteristica(Long id) throws ResourceNotFoundException{
        Optional<Characteristic> caracteristicaBuscada=characteristicRepository.findById(id);
        if (caracteristicaBuscada.isPresent()){
            log.info("Comenzo el proceso: buscando caracteristica");
            return objectMapper.convertValue(caracteristicaBuscada,CharacteristicDTO.class);
        }
        else {
            throw new ResourceNotFoundException("No se encontro la caracteristica con id= "+id+". Error al ingresar el id.");

        }
    }

    @Override
    public void eliminarCaracteristica(Long id) {
        characteristicRepository.deleteById(id);
    }

    @Override
    public List<CharacteristicDTO> agregarCaracteristicas(List<CharacteristicDTO> characteristics) {
        //List<Characteristic> allCharacteristics = characteristicRepository.findAll();
        List<CharacteristicDTO> allCharacteristicsDTO = new ArrayList<>();
        for(CharacteristicDTO characteristic: characteristics)
            allCharacteristicsDTO.add(agregarCaracteristica(characteristic));

        return allCharacteristicsDTO;
    }
}
