package peaksoft.dto.response;

import lombok.Builder;

@Builder
public record ProfileResponse(
        String name,
        String email,
        int countOfPost,
        String avatar
) {
}
