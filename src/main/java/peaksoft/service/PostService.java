package peaksoft.service;

import peaksoft.dto.request.PostRequest;
import peaksoft.dto.response.SimpleResponse;

public interface PostService {
    SimpleResponse createPost(PostRequest request);
}
