package monkeyforest.blog.web.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import monkeyforest.blog.domain.post.entity.Post;

import java.util.Random;

@Data
public class PostCreateForm {
    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    @Size(max = 20_000)
    private String body;

    public Post toPost() {
        return new Post(title, body, "username" + (new Random().nextInt(10) + 1));
    }
}
