package com.dh.PIG11.controller;

import com.dh.PIG11.dto.UserDTO;
import com.dh.PIG11.entities.User;
import com.dh.PIG11.exceptions.BadRequestException;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.security.IUserService;
import com.dh.PIG11.service.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private static final Logger logger = Logger.getLogger(IUserService.class);
    @Autowired
    private final IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> listarUsuarios(){
        logger.info("Comenzo el proceso: listando usuarios");
        return ResponseEntity.ok(userService.listarUsuarios());
    }
    @PostMapping
    public ResponseEntity<?> registrarNuevoUsuario(@RequestBody UserDTO user) {
        logger.info("Comenzo el proceso: guardando usuario");
        String passwordEncrypt = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncrypt);
        userService.validatedEmail(user.getEmail());
        //return ResponseEntity.created(URI.create("/users")).body(userService.agregarUsuario(user));
        userService.agregarUsuario(user);
        return ResponseEntity.ok(HttpStatus.CREATED);

    }



    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> buscarUserXid(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<UserDTO> usuarioBuscado= Optional.ofNullable(userService.buscarUsuario(id));
        if (usuarioBuscado.isPresent()){
            logger.info("Comenzo el proceso: buscando usuario");
            return ResponseEntity.ok(usuarioBuscado.get());
        }
        else {
            throw new ResourceNotFoundException("No se encontro el usuario con id= "+id+". Error al ingresar el id.");

        }}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Optional<UserDTO> usuarioBuscado= Optional.ofNullable(userService.buscarUsuario(id));
        if(usuarioBuscado.isPresent()){
            userService.eliminarUsuario(id);
            return ResponseEntity.ok("Se elimino el usuario con id= "+ id);
        }
        else{
            throw new ResourceNotFoundException("No se encontro el ususario con id= "+id+" para eliminarlo. Error al ingresar el id.");

        }
}}
