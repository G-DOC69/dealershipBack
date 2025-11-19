package com.auto.dealership.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminAuthController {

    @Value("${app.admin.code}")
    private String adminCodeHash;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Map<String, String> body,
            HttpSession session
    ) {
        String code = body.get("code");

        if (!passwordEncoder.matches(code, adminCodeHash)) {
            return ResponseEntity.status(401).body("Invalid admin code");
        }

        session.setAttribute("isAdmin", true);
        return ResponseEntity.ok("Admin login successful");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out");
    }
}
