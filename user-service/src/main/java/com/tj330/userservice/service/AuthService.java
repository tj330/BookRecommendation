package com.tj330.userservice.service;
import com.tj330.userservice.dto.*;
import com.tj330.userservice.entity.User;
import com.tj330.userservice.exception.EmailAlreadyTakenException;
import com.tj330.userservice.mapper.UserMapper;
import com.tj330.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService  {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserRegisterResponse signupAsUser(@RequestBody UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyTakenException("Email already exists");
        }

        User newUser = userMapper.toEntity(request);
        newUser.setRole("USER");
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);

        return userMapper.toResponse(newUser);
    }

    public UserRegisterResponse signupAsAdmin(@RequestBody UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyTakenException("Email already exists");
        }

        User newUser = userMapper.toEntity(request);
        newUser.setRole("ADMIN");
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);

        return userMapper.toResponse(newUser);
    }

    public UserLoginResponse authenticate(@RequestBody UserLoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new UserLoginResponse(
                token,
                "Bearer",
                refreshToken,
                user.getUsername(),
                user.getRole()
        );
    }

    public UserLoginResponse refreshToken(@RequestBody UserRefreshRequest request) {
        String refreshToken = request.refreshToken();
        String username = jwtService.extractUsername(refreshToken);

        var user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid refresh token"));

        if (jwtService.isTokenValid(refreshToken, user)) {
            var accessToken = jwtService.generateToken(user);

            return new UserLoginResponse(
                    accessToken,
                    "Bearer",
                    refreshToken,
                    username,
                    user.getRole()
            );
        }

        throw new RuntimeException("Invalid refresh token");
    }
}
