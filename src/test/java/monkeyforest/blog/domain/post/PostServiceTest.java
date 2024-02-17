package monkeyforest.blog.domain.post;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @InjectMocks
    PostService postService;
    @Mock
    PostRepository postRepository;

    @Test
    void create() {
        // Given
        var id = 1L;
        var title = "title";
        var body = "body";
        given(postRepository.save(any()))
                .willReturn(Post.builder()
                        .id(id)
                        .title(title)
                        .body(body)
                        .build());
        // When
        var post = postService.create(title, body);
        // Then
        assertThat(post.getId()).isEqualTo(id);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getBody()).isEqualTo(body);
    }

    @Test
    void update() {
        // Given
        var id = 1L;
        var newTitle = "newTitle";
        var newBody = "newBody";
        given(postRepository.findById(id))
                .willReturn(Optional.of(
                        Post.builder()
                                .id(id)
                                .title("title")
                                .body("body")
                                .build()));
        // When
        var post = postService.update(id, newTitle, newBody);
        // Then
        assertThat(post.getTitle()).isEqualTo(newTitle);
        assertThat(post.getBody()).isEqualTo(newBody);
    }

    @Test
    void update_notExistId() {
        // Given
        var id = 1L;
        var newTitle = "newTitle";
        var newBody = "newBody";
        given(postRepository.findById(id))
                .willReturn(Optional.empty());
        // When
        assertThatThrownBy(() -> postService.update(id, newTitle, newBody))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void delete() {
        postService.delete(1L);
    }
}