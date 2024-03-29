package monkeyforest.blog.domain.post.repository;

import monkeyforest.blog.domain.JpaConfig;
import monkeyforest.blog.domain.post.entity.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JpaConfig.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    void pagination() {
        // Given
        Post post1 = postRepository.save(Post.builder()
                .title("title1")
                .body("body1")
                .writer("username1")
                .build());
        Post post2 = postRepository.save(Post.builder()
                .title("title2")
                .body("body2")
                .writer("username2")
                .build());
        // When
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id")));
        // Then
        assertThat(page.getTotalPages()).isEqualTo(1);
        assertThat(page.getTotalElements()).isEqualTo(2);
        assertThat(page.getContent().size()).isEqualTo(2);
        assertThat(page.getContent().get(0).getTitle()).isEqualTo(post2.getTitle());
        assertThat(page.getContent().get(1).getTitle()).isEqualTo(post1.getTitle());
    }
}