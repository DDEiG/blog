package monkeyforest.blog.domain.post.service.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long postId) {
        super(String.format("Post with id %d not found", postId));
    }
}
