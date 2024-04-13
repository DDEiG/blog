package monkeyforest.blog.domain.post;

import monkeyforest.blog.domain.post.persistence.entity.Post;
import monkeyforest.blog.domain.post.persistence.repository.PostRepository;
import monkeyforest.blog.domain.post.service.PostService;
import monkeyforest.blog.domain.post.service.parameters.CreatePostParameters;
import monkeyforest.blog.domain.post.service.parameters.UpdatePostParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        var writer = "username";
        var parameters = new CreatePostParameters(title, body, writer);
        Post post = Post.builder()
                .id(id)
                .title(title)
                .body(body)
                .writer(writer)
                .build();
        given(postRepository.save(any()))
                .willReturn(post);
        // When
        post = postService.createPost(parameters);
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
        Page<Post> posts = postService.findPosts(PageRequest.of(0, 10));
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
                                .version(0L)
                                .build()));
        // When
        var post = postService.updatePost(new UpdatePostParameters(id, newTitle, newBody, 0L));
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
        assertThatThrownBy(() -> postService.updatePost(new UpdatePostParameters(id, newTitle, newBody, 0L)))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void delete() {
        postService.deletePost(1L);
    }
}