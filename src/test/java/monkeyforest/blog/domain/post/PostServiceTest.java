package monkeyforest.blog.domain.post;

import monkeyforest.blog.domain.post.entity.Post;
import monkeyforest.blog.domain.post.repository.PostRepository;
import monkeyforest.blog.domain.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
        var post = postService.createPost(title, body);
        // Then
        assertThat(post.getId()).isEqualTo(id);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getBody()).isEqualTo(body);
    }

    @Test
    void findPosts() {
        // Given
        given(postRepository.findAll((Pageable) any()))
                .willReturn(Page.empty());
        // When
        Page<Post> posts = postService.findPosts(0, 10);
        // Then
        assertThat(posts.getTotalElements()).isEqualTo(0);
        assertThat(posts.getTotalPages()).isEqualTo(1);
    }

    @Test
    void find() {
        // Given
        var id = 1L;
        var title = "title";
        var body = "body";
        given(postRepository.findById(id))
                .willReturn(Optional.of(
                        Post.builder()
                                .id(id)
                                .title(title)
                                .body(body)
                                .build()));
        // When
        var post = postService.findPost(id);
        // Then
        assertThat(post.getId()).isEqualTo(id);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getBody()).isEqualTo(body);
    }

    @Test
    void find_notExistId() {
        // Given
        given(postRepository.findById(any()))
                .willReturn(Optional.empty());
        // When
        assertThatThrownBy(() -> postService.findPost(1L))
                        .isInstanceOf(NoSuchElementException.class);
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
        var post = postService.updatePost(id, newTitle, newBody);
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
        assertThatThrownBy(() -> postService.updatePost(id, newTitle, newBody))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void delete() {
        postService.deletePost(1L);
    }
}