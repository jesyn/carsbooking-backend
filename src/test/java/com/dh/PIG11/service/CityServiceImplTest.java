/*
package com.dh.PIG11.service;

import com.dh.PIG11.entities.Category;
import com.dh.PIG11.entities.City;
import com.dh.PIG11.service.ICategoryService;
import com.dh.PIG11.service.ICityService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CityServiceImplTest {
    @Autowired
    private ICityService cityService;
    @Test
    @Order(1)
    public void agregarCiudadTest(){

        //preparo ciudad para prueba
        City ciudadParaAgregar = new City(null,"Rosario","Santa Fe", "Argentina","location","longitud");

        //uso el service
        City ciudadAgregada=cityService.agregarCiudad(ciudadParaAgregar);
        //realizo el assert
        assertEquals(ciudadParaAgregar,ciudadAgregada);
        cityService.eliminarCiudad(ciudadAgregada.getId());

    }
    @Test
    @Order(2)
    public void listarTodasCiudadesTest(){

        //uso el service
        List<City> ciudades= cityService.listarCiudades();
        System.out.println("Se listaron las siguientes ciudades: ");
        for (City o:ciudades) {
            System.out.println( o.getCity()+": "+o.getProvince() +" "+ o.getCountry());
        }
        //realizo el assert
        assertTrue(ciudades.size()>0);
    }



}

 */