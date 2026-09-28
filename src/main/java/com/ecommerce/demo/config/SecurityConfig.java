package com.ecommerce.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Aplica la configuración explícita de CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 2. Desactiva CSRF (necesario para apis REST stateless)
                .csrf(csrf -> csrf.disable())

                // 3. Permite todas las peticiones libremente en desarrollo
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Permite explícitamente el origen del Frontend (Vite / React)
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));

        // Permite todos los métodos HTTP requeridos para el CRUD
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));

        // Permite todas las cabeceras (Content-Type, Authorization, etc.)
        configuration.setAllowedHeaders(List.of("*"));

        // Permite el envío de credenciales/cookies si fuera necesario
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica esta regla de CORS a todos los endpoints (/**)
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
