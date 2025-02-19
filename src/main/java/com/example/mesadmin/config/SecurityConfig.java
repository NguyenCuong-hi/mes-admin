package com.example.mesadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINTS = {
            "/users", "/auth/token", "/auth/introspect", "/auth/logout", "/auth/refresh"
    };

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private CustomJwtDecode decode;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> {
                    try {
                        request
                                .antMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .httpBasic()
                                .and()
                                .sessionManagement();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(config -> config.decoder(decode)
                .jwtAuthenticationConverter(authConverter()))
                .authenticationEntryPoint(new AuthEntryPoint() {
                }));

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors().disable();
        return http.build();
    }

    @Bean
    JwtAuthenticationConverter authConverter(){
        JwtGrantedAuthoritiesConverter grantAuthConverter = new JwtGrantedAuthoritiesConverter();
        grantAuthConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter authConvert = new JwtAuthenticationConverter();
        authConvert.setJwtGrantedAuthoritiesConverter(grantAuthConverter);
        return authConvert;
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailService);
        auth.authenticationProvider(provider);
    }

}
