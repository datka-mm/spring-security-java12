package peaksoft.dto.request;

import java.util.List;

public record PostRequest(
        String description,
        List<String> images
) {
}
