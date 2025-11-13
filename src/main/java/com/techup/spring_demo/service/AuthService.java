package com.techup.spring_demo.service;

import com.techup.spring_demo.entity.User;
import com.techup.spring_demo.repository.UserRepository;
import com.techup.spring_demo.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final BCryptPasswordEncoder passwordEncoder;

  // ✅ สมัครสมาชิกใหม่
  public String register(String email, String password) {
    if (userRepository.findByEmail(email).isPresent()) {
      throw new RuntimeException("Email already exists");
    }

    User user = User.builder()
        .email(email)
        .password(passwordEncoder.encode(password))
        .build();

    userRepository.save(user);
    return "Registered successfully";
  }

  // ✅ เข้าสู่ระบบและสร้าง token
  public String login(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new RuntimeException("Invalid credentials");
    }

    return jwtService.generateToken(user.getEmail());
  }
}