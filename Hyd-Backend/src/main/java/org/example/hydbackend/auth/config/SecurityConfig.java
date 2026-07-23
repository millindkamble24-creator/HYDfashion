package org.example.hydbackend.auth.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        System.out.println("====================MY SECURITY CONFIG LOADED===========================");
        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public org.springframework.boot.CommandLineRunner runner(
            org.springframework.security.core.userdetails.UserDetailsService uds) {

        return args -> {
            System.out.println("UserDetailsService = " + uds.getClass().getName());
        };
    }
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        UserDetails user = User.builder()
                .username("user")
                .password(encoder.encode("123456"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
    @Bean
    public CommandLineRunner checkUser(UserDetailsService uds) {
        return args -> {
            UserDetails user = uds.loadUserByUsername("user");
            System.out.println("Username = " + user.getUsername());
            System.out.println("Password = " + user.getPassword());
            System.out.println("Authorities = " + user.getAuthorities());
        };
    }

}
