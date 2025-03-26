package org.example.antraproject1.controller;

import org.example.antraproject1.security.JwtUtil;
import org.example.antraproject1.pojo.Role;
import org.example.antraproject1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @PostMapping(value="/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
            System.out.println(userDetails);
            final String token = jwtUtil.generateToken(userDetails);
            System.out.println(token);
            return ResponseEntity.ok(new AuthResponse(token));
        }catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        userService.registerUser(request.getUsername(), request.getPassword(), request.getRole());
        return ResponseEntity.ok("User registered successfully");
    }
}

class AuthRequest{
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
class AuthResponse{
    private String token;
    public AuthResponse(String token) {
        this.token = token;
    }
}
class RegisterRequest {
    private String username;
    private String password;
    private Role role;

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
}

