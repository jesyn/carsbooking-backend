package com.dh.PIG11.service;


import com.dh.PIG11.dto.BookingDTO;
import com.dh.PIG11.entities.Booking;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBookingService {
    ResponseEntity<Booking> agregarReserva(BookingDTO booking);

    List<BookingDTO> listarReservas();
    ResponseEntity<Booking> editarReserva(BookingDTO booking);
    void eliminarReserva (Long id);
    BookingDTO buscarReserva(Long id);
    List<BookingDTO> listarReservasXproducto(Long product_id);
    List<BookingDTO> listarReservasXusuario(Long user_id);



}
