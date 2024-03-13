package peaksoft.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import peaksoft.enums.Role;

@Builder
public record SignUpAndSingInResponse(
        String token,
        String email,
        String message,
        HttpStatus httpStatus,
        Role role
) {
}
