package monkeyforest.blog.domain.user.persistence.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.springframework.util.Assert;

@Embeddable
@Getter
public class PhoneNumber {
    private String phoneNumber;

    protected PhoneNumber() {
    }

    public PhoneNumber(String phoneNumber) {
        Assert.hasText(phoneNumber, "phoneNumber cannot be blank");
        this.phoneNumber = phoneNumber;
    }
}
