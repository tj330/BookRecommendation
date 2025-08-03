package com.tj330.userservice.service;
import com.tj330.userservice.dto.UserLoginRequest;
import com.tj330.userservice.dto.UserLoginResponse;
import com.tj330.userservice.dto.UserRegisterRequest;
import com.tj330.userservice.dto.UserRegisterResponse;
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

        return new UserLoginResponse(
                token,
                "Bearer",
                user.getUsername(),
                user.getRole()
        );
    }
}
