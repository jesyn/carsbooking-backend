package com.dh.PIG11.service;


import com.dh.PIG11.dto.CharacteristicDTO;
import com.dh.PIG11.entities.Characteristic;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICharacteristicService {
    CharacteristicDTO agregarCaracteristica(CharacteristicDTO characteristic);
    List<CharacteristicDTO> listarCaracteristicas();
    //List<Characteristic> listarCaracteristicasXproducto();
    CharacteristicDTO buscarCaracteristica(Long id);
    void eliminarCaracteristica (Long id);
    List<CharacteristicDTO> agregarCaracteristicas(List<CharacteristicDTO> characteristics);
}
