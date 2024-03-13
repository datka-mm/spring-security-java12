package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.request.RegisterRequest;
import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.response.SignUpAndSingInResponse;
import peaksoft.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthApi {

    private final UserService userService;

    @PostMapping("/sign-up")
    public SignUpAndSingInResponse signUp(@RequestBody RegisterRequest request) {

        return userService.signUp(request);
    }

    @PostMapping("/sign-in")
    public SignUpAndSingInResponse signIn(@RequestBody SignInRequest request) {
        return userService.signIn(request);
    }
}
