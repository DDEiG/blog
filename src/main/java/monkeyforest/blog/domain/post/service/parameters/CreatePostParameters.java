package monkeyforest.blog.domain.post.service.parameters;

import monkeyforest.blog.domain.post.persistence.entity.Post;
import org.springframework.util.Assert;

public record CreatePostParameters (String title, String body, String writer) {

    public CreatePostParameters {
        Assert.hasText(title, "Title must not be null");
        Assert.hasText(body, "Title must not be null");
        Assert.hasText(writer, "Title must not be null");
    }

    public Post toPost() {
        return new Post(title, body, writer);
    }
}
