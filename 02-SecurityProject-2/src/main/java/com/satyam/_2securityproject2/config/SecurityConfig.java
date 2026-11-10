package com.satyam._2securityproject2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    // StateFull To StateLess -> csrf Token not in  Picture.

    @Bean
    public SecurityFilterChain securityFiltersChain(HttpSecurity http)
            throws Exception {

        http.csrf(customizer -> customizer.disable());

        http.authorizeHttpRequests(authorizeHttp -> authorizeHttp
                .requestMatchers("/add-user").permitAll().anyRequest().authenticated());
        http.httpBasic(Customizer.withDefaults());

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.authenticationProvider(authProvider());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authProvider() {

        DaoAuthenticationProvider daoProvider =
                new DaoAuthenticationProvider(userDetailsService);
        daoProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return daoProvider;
    }
}
