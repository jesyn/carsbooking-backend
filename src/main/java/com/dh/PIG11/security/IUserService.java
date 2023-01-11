package com.dh.PIG11.security;


import com.dh.PIG11.dto.UserDTO;
import com.dh.PIG11.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity<User> agregarUsuario(UserDTO user);
    List<UserDTO> listarUsuarios();
    void eliminarUsuario (Long id);
    UserDTO buscarUsuario(Long id);
    public User findByEmail(String email);

    public void validatedEmail(String email);


}
