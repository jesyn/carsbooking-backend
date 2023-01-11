package com.dh.PIG11.service;

import com.dh.PIG11.dto.BookingDTO;
import com.dh.PIG11.entities.Booking;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.repository.BookingRepository;
import com.dh.PIG11.repository.ProductRepository;
import com.dh.PIG11.security.RoleRepository;
import com.dh.PIG11.security.UserRepository;
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
@Qualifier("BookingServiceImpl")
@Service
public class BookingServiceImpl implements IBookingService{
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper;


    @Override
    public ResponseEntity<Booking> agregarReserva(BookingDTO booking) {
        Booking newBooking=  objectMapper.convertValue(booking, Booking.class);
        return ResponseEntity.ok(bookingRepository.save(newBooking));
    }

    @Override
    public List<BookingDTO> listarReservas() {
        List<Booking> allBookings = bookingRepository.findAll();
        List<BookingDTO> allBookingsDTO = new ArrayList<>();
        for(Booking booking: allBookings)
            allBookingsDTO.add(objectMapper.convertValue(booking,BookingDTO.class));

        return allBookingsDTO;
    }

    @Override
    public ResponseEntity<Booking> editarReserva(BookingDTO booking) {
        Booking editBooking=  objectMapper.convertValue(booking, Booking.class);
        return ResponseEntity.ok(bookingRepository.save(editBooking));
    }



    @Override
    public void eliminarReserva(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public BookingDTO buscarReserva(Long id) throws ResourceNotFoundException {

        Optional<Booking> reservaBuscada=bookingRepository.findById(id);
        if (reservaBuscada.isPresent()){
            log.info("Comenzo el proceso: buscando reserva");
            return objectMapper.convertValue(reservaBuscada, BookingDTO.class);
        }
        else {
            throw new ResourceNotFoundException("No se encontro la reserva con id= "+id+". Error al ingresar el id.");

        }
    }

    @Override
    public List<BookingDTO> listarReservasXproducto(Long product_id) {
        List<Booking> allBookingsXProduct = bookingRepository.findAllByProductId(product_id);
        List<BookingDTO> allBookingsDTO = new ArrayList<>();
        for(Booking booking: allBookingsXProduct)
            allBookingsDTO.add(objectMapper.convertValue(booking,BookingDTO.class));

        return allBookingsDTO;

    }

    @Override
    public List<BookingDTO> listarReservasXusuario(Long user_id) {
        List<Booking> allBookingsXUser = bookingRepository.findAllByUserId(user_id);
        List<BookingDTO> allBookingsDTO = new ArrayList<>();
        for(Booking booking: allBookingsXUser)
            allBookingsDTO.add(objectMapper.convertValue(booking,BookingDTO.class));

        return allBookingsDTO;

    }


}
