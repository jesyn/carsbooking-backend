package com.dh.PIG11.security;
import com.dh.PIG11.entities.User;
import com.dh.PIG11.security.jwt.JwtProviderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProviderConfig jwtProviderConfig;


    /**
     * Metodo para iniciar la obtención del token de acuerdo a la información del usuario
     * UserDTO lo recibo completo, aquí ustedes pueden crear su propio objeto, pero ojo
     * porque el envio de la información usuario y contraseña, estos dos deben si o si
     * existir en el DTO.
     *
     * */
    @PostMapping("/token")
    public ResponseEntity<Jwt> token(@RequestBody UserAuth userAuth) {


        /**
         * Usare la autenticación registrada en el ecocsistema de spring boot, donde
         * UsernamePasswordAuthenticationToken tiene prestablecidas las formas y los
         * metodos de busqueda y de encripción para el password.
         *
         * */
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(userAuth.getEmail(), userAuth.getPassword()));
        /**
         * Luego de realizar esa autenticación y de ver que el usuario si exista en bd, se procede
         * a registrar ese scope o ese usuario en el request, esto con el fin de darle prioridad
         * a la gestión del token
         * */
        SecurityContextHolder.getContext().setAuthentication(authentication);
        /**
         * Procedo a generar el token bajo el resultado de authentication, ya que aquí se encuentran los
         * resultados que me serán posibles setear y/o getear para la utilización y con esto y la información
         * obtenida se envía a generar el token
         * */
        String jwt = jwtProviderConfig.generateToken(authentication);
        /**
         * obtengo y casteo el usuario principal obtenido luego de la autenticación
         * */
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        /**
         * Devuelvo mi objeto, este objeto es creado por mi, ustedes pueden crear su propio objeto y pasarle
         * la información que ustedes deseen pasarle.
         * */
        Jwt jwt1 = new Jwt(jwt, "Bearer", userDetails.getUsername(), userAuth.getName(), userAuth.getLastname(), userDetails.getAuthorities());
        return ResponseEntity.ok(jwt1);




    }


}
