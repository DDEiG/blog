package monkeyforest.blog.web.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import monkeyforest.blog.domain.post.persistence.entity.Post;
import monkeyforest.blog.domain.post.service.parameters.UpdatePostParameters;

@Data
public class PostUpdateForm {
    @NotNull
    private Long id;
    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    @Size(max = 20_000)
    private String body;
    @NotNull
    private Long version;

    public static PostUpdateForm from(Post post) {
        var instance = new PostUpdateForm();
        instance.id = post.getId();
        instance.title = post.getTitle();
        instance.body = post.getBody();
        instance.version = post.getVersion();
        return instance;
    }

    public UpdatePostParameters toParameters() {
        return new UpdatePostParameters(id, title, body, version);
    }
}
