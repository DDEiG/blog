package monkeyforest.blog.domain.post.service;

import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.post.entity.Post;
import monkeyforest.blog.domain.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post create(String title, String body) {
        return postRepository.save(new Post(title, body));
    }

    @Transactional
    public Page<Post> find(int pageNumber, int pageSize) {
        return postRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id")));
    }

    @Transactional
    public Post find(Long id) {
        return postRepository.findById(id)
                .orElseThrow();
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
