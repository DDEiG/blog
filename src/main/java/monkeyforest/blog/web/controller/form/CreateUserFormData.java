package monkeyforest.blog.web.controller.form;

import lombok.Getter;
import lombok.Setter;
import monkeyforest.blog.domain.user.persistence.entity.field.Email;
import monkeyforest.blog.domain.user.persistence.entity.field.Gender;
import monkeyforest.blog.domain.user.persistence.entity.field.PhoneNumber;
import monkeyforest.blog.domain.user.persistence.entity.field.UserName;
import monkeyforest.blog.domain.user.service.parameters.CreateUserParameters;

import java.time.LocalDate;

@Getter
@Setter
public class CreateUserFormData {
    private String username;
    private String password;
    private String nickname;
    private Gender gender;
    private LocalDate birthday;

    public CreateUserParameters toParameters() {
        return new CreateUserParameters(new Email(username),
                password,
                nickname,
                gender,
                birthday);
    }
}
