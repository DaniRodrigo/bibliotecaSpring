package com.drr.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadWeb{


    final String[] WHITELIST = {
            "/css/*",
            "/js/*",
            "/img/*",
            "/**"

    };

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(WHITELIST).permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/customer").hasRole("USER")
                                .anyRequest().authenticated())
                .formLogin(form ->
                        form.loginPage("/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/"))
                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .permitAll());




        return http.build();
    }

}
