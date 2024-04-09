package monkeyforest.blog;

import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.post.entity.Post;
import monkeyforest.blog.domain.post.repository.PostRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile("local")
@Component
@RequiredArgsConstructor
public class DataInit implements ApplicationRunner {
    private final PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) {
        long count = postRepository.count();
        if (count != 0) {
            return;
        }
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            posts.add(Post.builder()
                    .title("title" + i)
                    .body("body" + i)
                    .writer("username" + i)
                    .build());
        }
        postRepository.saveAll(posts);
    }
}
