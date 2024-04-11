package monkeyforest.blog.domain.post.service;

import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.post.entity.Post;
import monkeyforest.blog.domain.post.repository.PostRepository;
import monkeyforest.blog.domain.post.service.parameters.CreatePostParameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post createPost(CreatePostParameters parameters) {
        return postRepository.save(parameters.toPost()); // TODO: 회원정보 자동 저장
    }

    @Transactional
    public Page<Post> findPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional
    public Page<Post> searchPosts(String title, Pageable pageable) {
        return postRepository.searchPost(title + "%", pageable);
    }

    @Transactional
    public Post findPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow();
    }

    @Transactional
    public Post updatePost(Long id, String title, String body) {
        var post = postRepository.findById(id)
                .orElseThrow();
        post.update(title, body);
        return post;
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
