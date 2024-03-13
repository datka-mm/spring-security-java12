package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dto.request.PostRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.model.Post;
import peaksoft.model.User;
import peaksoft.repository.PostRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.PostService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private User getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.getUserByEmail(email);
    }

    @Transactional
    @Override
    public SimpleResponse createPost(PostRequest request) {

        User user = getUser();
        Post savedPost = postRepository.save(Post.builder()
                .description(request.description())
                .images(request.images())
                .createdDate(LocalDate.now())
                .user(user)
                .build());

        user.getPosts().add(savedPost);
        return SimpleResponse.builder()
                .message("Post saved!")
                .build();
    }
}
