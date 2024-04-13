package monkeyforest.blog.domain.post.repository;

import monkeyforest.blog.domain.JpaConfig;
import monkeyforest.blog.domain.post.persistence.entity.Post;
import monkeyforest.blog.domain.post.persistence.repository.PostRepository;
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

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JpaConfig.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    void pagination() {
        // Given
        Post post1 = postRepository.save(new Post("title1", "body1", "username1"));
        Post post2 = postRepository.save(new Post("title2", "body2", "username2"));
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