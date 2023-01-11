package com.dh.PIG11.service;

import com.dh.PIG11.dto.CharacteristicDTO;

import com.dh.PIG11.entities.ProductCharacteristic;
import com.dh.PIG11.entities.ProductCharacteristicId;

import java.util.List;

public interface IProductCharacteristicService {

    List<ProductCharacteristic> agregarCharacteristics(List<CharacteristicDTO> characteristics, Long productId);
    ProductCharacteristic agregarProductCharacteristic(ProductCharacteristic id);
}
