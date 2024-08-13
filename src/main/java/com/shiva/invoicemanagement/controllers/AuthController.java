package com.shiva.invoicemanagement.controllers;

import com.shiva.invoicemanagement.config.JwtUtils;
import com.shiva.invoicemanagement.dto.JwtResponse;
import com.shiva.invoicemanagement.dto.LoginRequest;
import com.shiva.invoicemanagement.dto.SignupRequest;
import com.shiva.invoicemanagement.entities.Role;
import com.shiva.invoicemanagement.entities.User;
import com.shiva.invoicemanagement.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
//        System.out.println(loginRequest);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword());
//        System.out.println("Authenticating..." + authToken);
        Authentication authentication = authenticationManager.authenticate(authToken);
//        System.out.println("Received authentication object... " + authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }
        if (signUpRequest.getRole() == null) {
            signUpRequest.setRole(Role.ADMIN);
        }
        System.out.println(signUpRequest);
        User user = new User(signUpRequest.getUsername(),
                             passwordEncoder.encode(signUpRequest.getPassword()),
                                signUpRequest.getEmail(),
                             signUpRequest.getRole());

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK)
//                .header("Location", "/login")
                .body("User registered successfully!");
    }

}
