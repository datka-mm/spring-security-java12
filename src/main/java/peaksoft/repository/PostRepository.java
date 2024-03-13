package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}