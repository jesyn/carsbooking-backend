package com.dh.PIG11.security;

//import com.dh.PIG11.security.jwt.JwtAuthorizationFilter;
import com.dh.PIG11.security.jwt.JwtEntryPointConfig;
import com.dh.PIG11.security.jwt.JwtTokenFilterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtEntryPointConfig jwtEntryPointConfig;
    @Autowired
    private JwtEntryPointConfig unauthorizedHandler;

    //private final JwtAuthorizationFilter jwtAuthorizationFilter;



    /**
     * Registro de propiedades a implementar
     */
    @Bean
    public JwtTokenFilterConfig jwtTokenFilter() {
        return new JwtTokenFilterConfig();
    }

    //@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean("authenticationManager")
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    //@Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Registro de los endpoints, definiendo quien tiene acceso a cada uno de ellos, esto con el fin de darle
     * seguridad a nuestra aplicación
     */
    // @Override
    @Primary
    @Bean
    //ALGO PASA ACÁ
    protected HttpSecurity configure(HttpSecurity http) throws Exception {


        http.csrf().disable()

                .exceptionHandling().authenticationEntryPoint(jwtEntryPointConfig)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers(HttpMethod.POST,"/auth/**").permitAll()

                .antMatchers(HttpMethod.GET,"/auth/**").permitAll()
                .antMatchers(HttpMethod.POST,"/users/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/users/**").permitAll()
                .antMatchers(HttpMethod.GET,"/users/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/users/**").permitAll()
                .antMatchers(HttpMethod.POST,"/bookings/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.GET,"/bookings/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/bookings/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/bookings/**").permitAll()
                .antMatchers(HttpMethod.POST,"/cities/**").permitAll()
                .antMatchers(HttpMethod.GET,"/cities/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/cities/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/cities/**").permitAll()
                .antMatchers(HttpMethod.POST,"/products/**").permitAll() //.hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/products/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/products/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/products/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/products/**").permitAll()
                .antMatchers(HttpMethod.POST,"/categories/**").permitAll()//hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/categories/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/categories/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/categories/**").permitAll()
                .antMatchers(HttpMethod.POST,"/characteristics/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/characteristics/**").permitAll()
                .antMatchers(HttpMethod.GET,"/characteristics/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/characteristics/**").permitAll()
                .antMatchers(HttpMethod.POST,"/images/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/images/**").permitAll()
                .antMatchers(HttpMethod.GET,"/images/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/images/**").permitAll()
                .antMatchers(HttpMethod.POST,"/roles/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/roles/**").permitAll()
                .antMatchers(HttpMethod.GET,"/roles/**").permitAll()



                .anyRequest().authenticated();




        //.and()
        //.addFilterAfter(jwtAuthorizationFilter);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http;
    }

    /**
     * Se registran los cors origin para que el ecosistema permita el libre consumo de los endpoints desde
     * el front
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        //config.setAllowedOrigins(Arrays.asList("http://localhost:4200", "*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        UrlBasedCorsConfigurationSource cors = new UrlBasedCorsConfigurationSource();
        cors.registerCorsConfiguration("/**", config);
        return cors;
    }

    /**
     * Registro los filtros configurados anteriormente para que sea un filter implementado por sprinb
     * de esta manera uso e implemento el registro y apertura de los cors
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
    }

