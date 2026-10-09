package com.ecommerce.demo.login.controller;

import com.ecommerce.demo.config.JwtUtil;
import com.ecommerce.demo.login.dto.LoginRequestDTO;
import com.ecommerce.demo.login.dto.LoginResponseDTO;
import com.ecommerce.demo.login.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String token = jwtUtil.generateToken(user.getUsername(), user.getRoleEnum().getAuthority());

            return ResponseEntity.ok(new LoginResponseDTO(token, user.getUsername(), user.getRoleEnum().getAuthority()));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Credenciales inválidas. Usuario o contraseña incorrectos."));
        }
    }
}
