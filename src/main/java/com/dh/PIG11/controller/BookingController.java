package com.dh.PIG11.controller;

import com.dh.PIG11.dto.BookingDTO;
import com.dh.PIG11.dto.UserDTO;
import com.dh.PIG11.entities.Booking;
import com.dh.PIG11.entities.User;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.security.UserAuth;
import com.dh.PIG11.service.IBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/bookings")
@CrossOrigin
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> buscarReservasXId(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<BookingDTO> reservaBuscada= Optional.ofNullable(bookingService.buscarReserva(id));
        if (reservaBuscada.isPresent()){
            log.info("Comenzo el proceso: buscando reserva");
            return ResponseEntity.ok(reservaBuscada.get());
        }
        else {
            throw new ResourceNotFoundException("No se encontro la reserva con id= "+id+". Error al ingresar el id.");

        }

    }
    @GetMapping
    public ResponseEntity<List<BookingDTO>> listarReservas(){
        log.info("Comenzo el proceso: listando reservas");
        return ResponseEntity.ok(bookingService.listarReservas());
    }

    @PostMapping//("/newBooking")
    public ResponseEntity<?> registrarNuevaReserva(@RequestBody BookingDTO booking){

        log.info("Comenzo el proceso: guardando categoria");

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        booking.setUser(new UserDTO());
        booking.getUser().setId(((UserAuth)authentication.getPrincipal()).getId());
        return ResponseEntity.ok(bookingService.agregarReserva(booking));
    }

    @PutMapping
    public ResponseEntity<?> editarReserva(@RequestBody BookingDTO booking) throws ResourceNotFoundException {
        Optional<BookingDTO> reservaBuscada= Optional.ofNullable(bookingService.buscarReserva(booking.getId()));
        if (reservaBuscada.isPresent()){
            log.info("Se edito la reserva");
            return ResponseEntity.ok(bookingService.editarReserva(booking));
        }
        else {
            throw new ResourceNotFoundException("No se encontro la reserva con id= "+ booking.getId()+" para editarla. Error al ingresar el id.");

        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReserva(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<BookingDTO> reservaBuscada= Optional.ofNullable(bookingService.buscarReserva(id));
        if(reservaBuscada.isPresent()){
            bookingService.eliminarReserva(id);
            return ResponseEntity.ok("Se elimino la reserva con id= "+ id);
        }
        else{
            throw new ResourceNotFoundException("No se encontro la reserva con id= "+id+" para eliminarla. Error al ingresar el id.");

        }

    }
    @GetMapping("product/{product_id}")
    public ResponseEntity<List<BookingDTO>> listarReservasXProducto(@PathVariable("product_id") Long product_id) {
        log.info("Comenzo el proceso: listando reservas por producto");
        return ResponseEntity.ok(bookingService.listarReservasXproducto(product_id));


}
    @GetMapping("user/{user_id}")
    public ResponseEntity<List<BookingDTO>> listarReservasXUsuario(@PathVariable("user_id") Long user_id) {
        log.info("Comenzo el proceso: listando reservas por usuario");
        return ResponseEntity.ok(bookingService.listarReservasXusuario(user_id));}

}
