package com.hospital.Hospital_Managment.security;

import com.hospital.Hospital_Managment.dto.LoginRequestDto;
import com.hospital.Hospital_Managment.dto.LoginResponseDto;
import com.hospital.Hospital_Managment.dto.SignupResponseDto;
import com.hospital.Hospital_Managment.entity.User;
import com.hospital.Hospital_Managment.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token,user.getId());
    }

    public SignupResponseDto signup(LoginRequestDto signupRequestDto) {

        Optional<User> existingUser = userRepository.findByUsername(signupRequestDto.getUsername());
        if(existingUser.isPresent()){
            throw new RuntimeException("User found with this username!");
        }

        User user = userRepository.save(User.builder()
                .username(signupRequestDto.getUsername())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build()
        );

        return new SignupResponseDto(user.getId(),user.getUsername());
    }
}
