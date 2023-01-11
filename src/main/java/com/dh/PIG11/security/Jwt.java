package com.dh.PIG11.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Jwt {
    private String token;
    private String bearer;
    private String userName;
    private String name;
    private String lastName;
    private Collection<? extends GrantedAuthority> authorities;
}
