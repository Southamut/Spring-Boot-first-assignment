package com.techup.spring_demo.controller;

import com.techup.spring_demo.security.JwtService;
import com.techup.spring_demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final JwtService jwtService;

  // ✅ Register
  @PostMapping("/register")
  public String register(@RequestBody Map<String, String> req) {
    return authService.register(req.get("email"), req.get("password"));
  }

  // ✅ Login
  @PostMapping("/login")
  public Map<String, String> login(@RequestBody Map<String, String> req) {
    String token = authService.login(req.get("email"), req.get("password"));
    return Map.of("token", token);
  }

  // ✅ /auth/me — แสดง email จาก token
  @GetMapping("/me")
  public Map<String, String> me(@RequestHeader("Authorization") String header) {
    String token = header.replace("Bearer ", "");
    String email = jwtService.extractEmail(token);
    return Map.of("email", email);
  }
}