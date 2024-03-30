package monkeyforest.blog.web.controller.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import monkeyforest.blog.domain.post.entity.Post;

@Data
public class PostUpdateForm {
    @NotNull
    private Long id;
    @NotEmpty
    @Size(max = 50)
    private String title;
    @NotEmpty
    @Size(max = 20_000)
    private String body;

    public static PostUpdateForm from(Post post) {
        var instance = new PostUpdateForm();
        instance.id = post.getId();
        instance.title = post.getTitle();
        instance.body = post.getBody();
        return instance;
    }
}
