package com.dh.PIG11.service;

import com.dh.PIG11.dto.CharacteristicDTO;
import com.dh.PIG11.entities.Characteristic;
import com.dh.PIG11.entities.Product;
import com.dh.PIG11.entities.ProductCharacteristic;
import com.dh.PIG11.entities.ProductCharacteristicId;
import com.dh.PIG11.repository.CharacteristicRepository;
import com.dh.PIG11.repository.ProductCharacteristicRepository;
import com.dh.PIG11.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductCharacteristicServiceImpl implements IProductCharacteristicService {

    @Autowired
    ProductCharacteristicRepository productCharacteristicRepository;
    @Autowired
    CharacteristicRepository  characteristicRepository;
    @Autowired
    ProductRepository productRepository;


    @Override
    public List<ProductCharacteristic> agregarCharacteristics(List<CharacteristicDTO> characteristics, Long productId) {

    List<ProductCharacteristic> allProductsCharacteristics = new ArrayList<>();
    for(CharacteristicDTO characteristic: characteristics){
        ProductCharacteristicId productCharacteristicId = new ProductCharacteristicId(characteristic.getId(), productId);
        Product product = new Product();
        product.setId(productId);
        Characteristic characteristic1 = new Characteristic();
        characteristic1.setId(characteristic.getId());
        ProductCharacteristic productCharacteristic = new ProductCharacteristic( productCharacteristicId ,product, characteristic1);
        allProductsCharacteristics.add(agregarProductCharacteristic(productCharacteristic));
    }
        return allProductsCharacteristics;
}

    @Override
    public ProductCharacteristic agregarProductCharacteristic(ProductCharacteristic id) {

        return productCharacteristicRepository.save(id);
    }
}
