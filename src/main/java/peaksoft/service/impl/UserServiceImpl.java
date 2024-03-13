package peaksoft.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtService;
import peaksoft.dto.request.RegisterRequest;
import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.response.ProfileResponse;
import peaksoft.dto.response.SignUpAndSingInResponse;
import peaksoft.enums.Role;
import peaksoft.model.User;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @PostConstruct
    public void initMethod() {
        userRepository.save(
                new User("Admin", "admin@gmail.com", passwordEncoder.encode("admin"), "avatar", Role.ADMIN));
    }

    @Override
    public SignUpAndSingInResponse signUp(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new BadCredentialsException("User with " + request.email() + " already exists");
        }

        User savedUser = userRepository.save(User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build());

        return SignUpAndSingInResponse.builder()
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .token(jwtService.createToken(savedUser))
                .httpStatus(HttpStatus.OK)
                .message("Success")
                .build();
    }

    @Override
    public SignUpAndSingInResponse signIn(SignInRequest request) {

        User user = userRepository.getUserByEmail(request.email());

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        return SignUpAndSingInResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtService.createToken(user))
                .httpStatus(HttpStatus.OK)
                .message("sign in")
                .build();
    }

    @Override
    public ProfileResponse getProfile(Principal principal) {
        String email = principal.getName();
        User user = userRepository.getUserByEmail(email);
        return ProfileResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .countOfPost(user.getPosts().size())
                .build();
    }
}
