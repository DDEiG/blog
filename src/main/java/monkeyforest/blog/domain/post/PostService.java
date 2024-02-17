package monkeyforest.blog.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post create(String title, String body) {
        return postRepository.save(new Post(title, body));
    }

    @Transactional
    public Post update(Long id, String title, String body) {
        var post = postRepository.findById(id)
                .orElseThrow();
        post.update(title, body);
        return post;
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
