package monkeyforest.blog.domain.post.service.parameters;

import monkeyforest.blog.domain.post.persistence.entity.Post;
import org.springframework.util.Assert;

public record UpdatePostParameters (Long id, String title, String body) {
    public UpdatePostParameters {
        Assert.notNull(id, "Title must not be null");
        Assert.hasText(title, "Title must not be null");
        Assert.hasText(body, "Title must not be null");
    }

    public void update(Post post) {
        post.update(title, body);
    }
}
