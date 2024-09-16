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
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/test/all").permitAll()
                                .requestMatchers("/api/test/user").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/test/admin").hasAuthority("ADMIN")
                                .requestMatchers("/api/course/post").hasAuthority("ADMIN")
                                .requestMatchers("/api/course/put/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/course/get/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/course/get").hasAuthority("ADMIN")
                                .requestMatchers("/api/course/delete/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/experience/post").hasAuthority("ADMIN")
                                .requestMatchers("/api/experience/put/**").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/experience/get/**").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/experience/get").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/experience/delete/**").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/education/post").hasAuthority("ADMIN")
                                .requestMatchers("/api/education/put/**").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/education/get/**").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/education/get").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/education/delete/**").hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers("/api/user/post").hasAuthority("ADMIN")
                                .requestMatchers("/api/user/put/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/user/get/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/user/get").hasAuthority("ADMIN")
                                .requestMatchers("/api/user/delete/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/user/addEducation").hasAuthority("USER")
                                .requestMatchers("/api/user/addExperience").hasAuthority("USER")
                                .requestMatchers("/api/user/putOwner/{idUser}/course/{idCourse}").hasAuthority("ADMIN")
                                .requestMatchers("/api/user/addCourse/{idCourse}").hasAuthority("USER")
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
