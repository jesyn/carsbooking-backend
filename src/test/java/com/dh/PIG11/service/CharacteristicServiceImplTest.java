/*package com.dh.PIG11.service;

import com.dh.PIG11.entities.Characteristic;
import com.dh.PIG11.entities.Product;
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
public class CharacteristicServiceImplTest {
    @Autowired
    private CharacteristicServiceImpl characteristicService;
    @Test
    @Order(1)
    public void agregarCaracteristicaTest() {
        Characteristic characteristic=new Characteristic(200L,"motor híbrido","jdjfj");
        assertNotNull(characteristicService.agregarCaracteristica(characteristic));
        characteristicService.eliminarCaracteristica(characteristic.getId());
    }

    @Test
    @Order(2)
    public void listarCaracteristicas() {
        Characteristic characteristic=characteristicService.agregarCaracteristica(new Characteristic(100L,"motor híbrido","jdjfj"));
        assertNotNull(characteristicService.listarCaracteristicas());
        characteristicService.eliminarCaracteristica(characteristic.getId());
    }

    @Test
    @Order(3)
    public void buscaryEliminarCaracteristica() {
        Characteristic characteristic=characteristicService.agregarCaracteristica(new Characteristic(100L,"motor híbrido","jdjfj"));
        characteristicService.eliminarCaracteristica(characteristic.getId());
        Optional<Characteristic> caracteristicaBuscada=characteristicService.buscarCaracteristica(characteristic.getId());
        //assertTrue(caracteristicaBuscada.isEmpty());
    }


}*/
