package com.dh.PIG11.service;

import com.dh.PIG11.entities.Booking;
import com.dh.PIG11.entities.Product;
import com.dh.PIG11.entities.User;
import com.dh.PIG11.security.IUserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookingServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IBookingService bookingService;

    @Test
    @Order(1)
    public void agregarReserva() {
       /* Product product= new Product();
        User user=new User();
        Booking bookingPrueba= new Booking(13,new LocalDate(2022,11,21),new LocalDate(2022,11,25),product,user);
        assertNotNull(productService.agregarProducto(product));
        productService.eliminarProducto(product.getId());*/
    }

    @Test
    @Order(2)
    public void listarReservas() {
    }

    @Test
    @Order(3)
    public void editarReserva() {
    }

    @Test
    @Order(4)
    public void eliminarReserva() {
    }

    @Test
    @Order(5)
    public void buscarReserva() {
    }

    @Test
    @Order(6)
    public void listarReservasXproducto() {
    }
}