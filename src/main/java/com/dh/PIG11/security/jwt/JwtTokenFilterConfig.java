package com.dh.PIG11.security.jwt;


import com.dh.PIG11.security.UserDetailsServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class JwtTokenFilterConfig extends OncePerRequestFilter {

    @Autowired
    private JwtProviderConfig jwtProviderConfig;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    /**
     * Metodo que hace un filtrado de las solicitudes para que antes de que llegue al
     * recurso se valide si esta permitido o no el acceso a dicho recurso
     * */
    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = myToken(request);
        if (token != null && jwtProviderConfig.validateToken(token)) {
            /**
             * Inicia a revisar si el token tiene los permisos para ingresar a ese recurso
             * que estamos consumiendo, ver que tenga y cuente con los tiempos actualizados
             * de expiración y de integridad.
             * */
            String username = jwtProviderConfig.getUserNameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Metodo de apoyo para obtener el token que se esta enviando desde el front
     * Se hace un reemplazo del Bearer por un vacio "", esto para tener solamente el
     * token como tal
     * */
    public String myToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            return header.replace("Bearer ", "");
        }
        return null;
    }

}
