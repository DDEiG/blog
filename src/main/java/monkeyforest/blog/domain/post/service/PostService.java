package monkeyforest.blog.domain.post.service;

import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.post.entity.Post;
import monkeyforest.blog.domain.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post createPost(Post post) {
        post.update("username" + (new Random().nextInt(10) + 1));
        return postRepository.save(post); // TODO: 회원정보 자동 저장
    }

    @Transactional
    public Page<Post> findPosts(int pageNumber, int pageSize) {
        return postRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id")));
    }

    @Transactional
    public Page<Post> searchPosts(String title, int pageNumber, int pageSize) {
        return postRepository.searchPost(title + "%", PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id")));
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
