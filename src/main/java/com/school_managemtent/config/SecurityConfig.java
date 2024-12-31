package com.school_managemtent.config;
import com.school_managemtent.service.impl.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;

    public SecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/teacher").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/preceptor").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/directivo").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/relative").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/relative-student").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/course").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/course-preceptor").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/course-teacher").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/teacher-subject").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/course-subject").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/course-student").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/preceptor-student").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/student-subject").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/teacher-student").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.POST, "/api/subject").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.DELETE, "/api/teacher/**").hasRole("DIRECTIVO")
                        .requestMatchers(HttpMethod.GET, "/api/teacher/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/teachers").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/user").permitAll()
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
