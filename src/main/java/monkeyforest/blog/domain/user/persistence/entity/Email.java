package monkeyforest.blog.domain.user.persistence.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.Objects;

@Embeddable
@Getter
public class Email {
    private String email;

    protected Email() {
    }

    public Email(String email) {
        Assert.hasText(email, "email cannot be blank");
        Assert.isTrue(email.contains("@"), "email should contain @ symbol");
        this.email = email;
    }
}
