package monkeyforest.blog;

import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.post.entity.Post;
import monkeyforest.blog.domain.post.repository.PostRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements ApplicationRunner {
    private final PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) {
        postRepository.save(Post.builder()
                .title("title1")
                .body("body1")
                .username("username1")
                .build());
        postRepository.save(Post.builder()
                .title("title2")
                .body("body2")
                .username("username2")
                .build());
    }
}
