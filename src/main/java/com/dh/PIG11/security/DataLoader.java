package com.dh.PIG11.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private UserRepository userRepository;
    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String hash=passwordEncoder.encode("superprofe");
        //Usuario usuario=new Usuario("Rodolfo","Rodo","rodo@gmail.com",hash, UsuarioRole.ROLE_USER);
        //usuarioRepository.save(usuario);
        //String hash1=passwordEncoder.encode("superprofe");
        //Usuario usuarioAdmin=new Usuario("Rodolfo","RodoAdmin","rodo@gmail.com",hash1, UsuarioRole.ROLE_ADMIN);
        //usuarioRepository.save(usuarioAdmin);

    }
}
