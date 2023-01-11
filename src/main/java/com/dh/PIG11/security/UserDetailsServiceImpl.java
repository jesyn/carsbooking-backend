package com.dh.PIG11.security;

import com.dh.PIG11.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User usuarioBuscado = userRepository.findByEmail(email);

        GrantedAuthority authority = new SimpleGrantedAuthority(usuarioBuscado.getRole().getName());
        return UserAuth.builder().id(usuarioBuscado.getId()).name(usuarioBuscado.getName()).lastname(usuarioBuscado.getLastname()).email(usuarioBuscado.getEmail()).password(usuarioBuscado.getPassword()).authorities(Arrays.asList(authority)).build();
    }



}

