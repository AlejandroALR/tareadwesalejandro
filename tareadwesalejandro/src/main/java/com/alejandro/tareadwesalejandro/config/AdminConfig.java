package com.alejandro.tareadwesalejandro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Configuration
public class AdminConfig {

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Bean
    public InMemoryUserDetailsManager adminUserDetailsManager() {
        UserDetails admin = User.builder()
                .username(adminUsername)
                .password(adminPassword)
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_ADMIN")))
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
    

//    private final AppProperties appProperties;
//
//    public AdminConfig(AppProperties appProperties) {
//        this.appProperties = appProperties;
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager adminUserDetailsManager() {
//        UserDetails admin = User.builder()
//                .username(appProperties.getUsername())
//                .password(appProperties.getPassword())
//                .authorities(List.of(new SimpleGrantedAuthority("ROLE_ADMIN")))
//                .build();
//
//        return new InMemoryUserDetailsManager(admin);
//    }
}
