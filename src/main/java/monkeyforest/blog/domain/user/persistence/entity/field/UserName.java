package monkeyforest.blog.domain.user.persistence.entity.field;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.springframework.util.Assert;

@Embeddable
@Getter
public class UserName {
    private String firstName;
    private String lastName;

    protected UserName() {
    }

    public UserName(String firstName, String lastName) {
        Assert.hasText(firstName, "firstName cannot be blank");
        Assert.hasText(lastName, "lastName cannot be blank");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

}
