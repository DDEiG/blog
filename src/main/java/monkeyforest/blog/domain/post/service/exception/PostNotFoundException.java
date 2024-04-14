package monkeyforest.blog.domain.post.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // TODO: 404가 맞는가...
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long postId) {
        super(String.format("Post with id %d not found", postId));
    }
}
