package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.request.PostRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostApi {

    private final PostService postService;

    @PostMapping
    public SimpleResponse createPost(@RequestBody PostRequest request) {

        return postService.createPost(request);
    }
}
