package com.learnify_backend.service.impl;

import java.net.Authenticator;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learnify_backend.dto.JwtAuthResponse;
import com.learnify_backend.dto.LoginDto;
import com.learnify_backend.dto.RegisterDto;
import com.learnify_backend.dto.UserDto;
import com.learnify_backend.entity.OTP;
import com.learnify_backend.entity.Role;
import com.learnify_backend.entity.User;

import com.learnify_backend.repository.OtpRepository;
import com.learnify_backend.repository.RoleRepository;
import com.learnify_backend.repository.UserRepository;
import com.learnify_backend.security.JwtTokenProvider;
import com.learnify_backend.service.AuthService;
import com.learnify_backend.service.EmailService;
import com.learnify_backend.utils.OtpUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;

    // OTP otp;
    public String register(RegisterDto dto) {
        boolean existByEmail = userRepository.existsByEmail(dto.getEmail());
        if (existByEmail)
            throw new RuntimeException("email already registered");

        OTP otpObj = otpRepository.findByEmail(dto.getEmail());

        if (otpObj == null) {
            throw new RuntimeException("OTP not found. Please request OTP first.");
        }

        if (otpObj.isExpired()) {
            otpRepository.delete(otpObj);
            throw new RuntimeException("OTP expired");
        }

        System.out.println("Entered OTP:........................ " + dto.getOtp());

        if (!otpObj.getOtp().equals(dto.getOtp()) ) {
            throw new RuntimeException("Invalid otp");
        }

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new RuntimeException("password do not match");
        }

        Set<String> allowedRoles = Set.of("STUDENT", "INSTRUCTOR");

        String roleName = dto.getRole();

        if (!allowedRoles.contains(roleName)) {
            throw new RuntimeException("Invalid role");
        }

        Role role = roleRepository.findByRoleName(roleName);
                

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setImage("https://api.dicebear.com/5.x/initials/svg?seed=" + dto.getFirstName());
        if(roleName != null){
            user.setRole(role);
        } else {
            throw new RuntimeException("Role not found");
        }
        
        // user.setPhoneNo(dto.getPhoneNo());
        // user.setImage(dto.getImage());

        userRepository.save(user);

        otpRepository.delete(otpObj);

        return "user created successfully";

    }

    @Override
    public JwtAuthResponse login(LoginDto dto) {
        System.out.println("LOGIN HIT above authenticator manager");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        System.out.println("LOGIN HIT");

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        String userRole = null;
        if (user.isPresent()) {
            User loggedInUser = user.get();
            userRole = loggedInUser.getRole().getRoleName();
        }
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.get().getFirstName());
        userDto.setLastName(user.get().getLastName());
        userDto.setImage(user.get().getImage());
        userDto.setRole(userRole);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setUser(userDto);
        response.setAccessToken(token);
        return response;
    }

    @Override
    @Transactional
    public void sendOtp(String email) {
        otpRepository.deleteByEmail(email);
        String otp = OtpUtil.generateOtp();
        OTP otpEntity = new OTP();

        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);

        otpRepository.save(otpEntity);

        System.out.println("Saved OTP: -------------------------------" + otpEntity.getOtp());
        

        emailService.sendOtp(email, otp);

    }
}
