package monkeyforest.blog.domain.user.persistence.entity.field;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.Objects;

@Embeddable
@Getter
public class Email {
    private String username;

    protected Email() {
    }

    public Email(String username) {
        Assert.hasText(username, "email cannot be blank");
        Assert.isTrue(username.contains("@"), "email should contain @ symbol");
        this.username = username;
    }
}
