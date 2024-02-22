package monkeyforest.blog.domain.post.repository;

import monkeyforest.blog.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
