package com.school_managemtent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails directivoUser = User.withUsername("admin")
                .password(passwordEncoder().encode("admin")) // encriptado con BCrypt
                .roles("DIRECTIVO")
                .build();

        return new InMemoryUserDetailsManager(directivoUser);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Solo los usuarios con rol DIRECTIVO pueden crear y eliminar usuarios con rol teacher
                        .requestMatchers(HttpMethod.POST, "/api/users/teacher").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/teacher/**").hasRole("DIRECTIVO")

                        // Para obtener uno o varios usuarios teacher, se podría requerir estar logueado:
                        .requestMatchers(HttpMethod.GET, "/api/users/teacher/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/users/teachers").authenticated()

                        // Cualquier otra ruta, la configuras según tu necesidad (aquí ejemplo):
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    /**
     * Ejemplo de PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
