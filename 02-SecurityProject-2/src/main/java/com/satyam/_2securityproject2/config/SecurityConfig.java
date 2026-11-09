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
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;


    // StateFull To StateLess -> csrf Token not in  Picture.

    @Bean
    public SecurityFilterChain securityFiltersChain(HttpSecurity http) {
        http.csrf(customizer -> customizer.disable());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(
                session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

//    // To Get Every User Should Get Unqine Username And We Will Verify That From DB.
//    @Bean
//    public UserDetailsService userDetails() {

    /// /        User.UserBuilder user = User.withDefaultPasswordEncoder();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("Vishal").password("vishal").roles("USER").build();
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("Hami").password("hami").roles("ADMIN").build();
//
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider daoprovider = new DaoAuthenticationProvider();
        daoprovider.setUserDetailsService(userDetailsService);

    }


}
