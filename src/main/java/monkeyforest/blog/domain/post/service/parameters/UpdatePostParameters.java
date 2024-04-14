package monkeyforest.blog.domain.post.service.parameters;

import monkeyforest.blog.domain.post.persistence.entity.Post;
import org.springframework.util.Assert;

public record UpdatePostParameters (Long id, String title, String body, Long version) {
    public UpdatePostParameters {
        Assert.notNull(id, "Id must not be null");
        Assert.hasText(title, "Title must not be null");
        Assert.hasText(body, "Body must not be null");
        Assert.notNull(version, "Version must not be null");
    }

    public void update(Post post) {
        post.update(title, body, version);
    }
}
