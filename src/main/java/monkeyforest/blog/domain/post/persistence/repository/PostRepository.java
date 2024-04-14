package monkeyforest.blog.domain.post.persistence.repository;

import monkeyforest.blog.domain.post.persistence.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p " +
            "where p.title like :title")
    Page<Post> searchPost(String title, Pageable pageable);
}
