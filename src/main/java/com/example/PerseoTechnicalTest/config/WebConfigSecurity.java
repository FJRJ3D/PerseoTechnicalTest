package com.example.PerseoTechnicalTest.config;

import com.example.PerseoTechnicalTest.jwt.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebConfigSecurity {
    private final AuthenticationProvider authenticationProvider;
    private final AuthTokenFilter authTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .csrf(csrf ->
                        csrf.disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/api/user/putOwner/{idUser}/course/{idCourse}").permitAll()
                                .requestMatchers("/api/user/addCourse/{idCourse}").permitAll()
                                .requestMatchers("/api/user/get").permitAll()
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/test/all").permitAll()
                                .requestMatchers("/api/test/user").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/test/admin").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/news/post").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/news/put/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/news/delete/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                        )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
