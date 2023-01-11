package com.dh.PIG11.service;

import com.dh.PIG11.dto.UserDTO;
import com.dh.PIG11.entities.User;
import com.dh.PIG11.exceptions.BadRequestException;
import com.dh.PIG11.exceptions.ResourceNotFoundException;
import com.dh.PIG11.security.UserRepository;
import com.dh.PIG11.security.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@Transactional
@Qualifier("UserServiceImpl")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper;


    @Override
    public ResponseEntity<User> agregarUsuario(UserDTO user) {
        User newUser= objectMapper.convertValue(user,User.class);
        return ResponseEntity.ok(userRepository.save(newUser));
    }

    @Override
    public List<UserDTO> listarUsuarios() {
        List<User> allUsers = userRepository.findAll();
        List<UserDTO> allUsersDTO = new ArrayList<>();
        for(User user: allUsers)
            allUsersDTO.add(objectMapper.convertValue(user,UserDTO.class));

        return allUsersDTO;
    }

    @Override
    public void eliminarUsuario(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO buscarUsuario(Long id) throws ResourceNotFoundException{
        Optional<User> usuarioBuscado=userRepository.findById(id);
        if (usuarioBuscado.isPresent()){
            log.info("Comenzo el proceso: buscando usuario");
            return objectMapper.convertValue(usuarioBuscado,UserDTO.class);
        }
        else {
            throw new ResourceNotFoundException("No se encontro el usuario con id= "+id+". Error al ingresar el id.");

        }
    }

    @Override
    public User findByEmail(String email) {
        log.info("email: " + email);
        User user= userRepository.findByEmail(email);
        //return userRepository.findByEmail(email);
        return user;
    }

    @Override
    public void validatedEmail(String email) throws BadRequestException {
        if(userRepository.validateEmail(email) == 1){
            throw new BadRequestException("El email " + email + " esta siendo utilizado por otro usuario");
        }

    }


}
