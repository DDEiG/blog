package monkeyforest.blog.domain.post.service;

import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.post.persistence.entity.Post;
import monkeyforest.blog.domain.post.persistence.repository.PostRepository;
import monkeyforest.blog.domain.post.service.exception.PostNotFoundException;
import monkeyforest.blog.domain.post.service.parameters.CreatePostParameters;
import monkeyforest.blog.domain.post.service.parameters.UpdatePostParameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
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
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    @Transactional
    public Post updatePost(UpdatePostParameters parameters) {
        var post = postRepository.findById(parameters.id())
                .orElseThrow();
        if (!post.getVersion().equals(parameters.version())) {
            throw new ObjectOptimisticLockingFailureException(Post.class, post.getId());
        }
        parameters.update(post);
        return post;
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
