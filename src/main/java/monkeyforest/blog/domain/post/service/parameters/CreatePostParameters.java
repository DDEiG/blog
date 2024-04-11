package monkeyforest.blog.domain.post.service.parameters;

import monkeyforest.blog.domain.post.entity.Post;

public record CreatePostParameters (String title, String body, String writer) {
    public Post toPost() {
        return new Post(title, body, writer);
    }
}
