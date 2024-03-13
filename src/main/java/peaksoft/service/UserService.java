package peaksoft.service;

import peaksoft.dto.request.RegisterRequest;
import peaksoft.dto.request.SignInRequest;
import peaksoft.dto.response.ProfileResponse;
import peaksoft.dto.response.SignUpAndSingInResponse;

import java.security.Principal;

public interface UserService {
    SignUpAndSingInResponse signUp(RegisterRequest request);

    SignUpAndSingInResponse signIn(SignInRequest request);

    ProfileResponse getProfile(Principal principal);

}
