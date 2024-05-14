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
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String password;
    private LocalDate birthday;
    private String phoneNumber;

    public CreateUserParameters toParameters() {
        return new CreateUserParameters(new UserName(firstName, lastName),
                password,
                gender,
                birthday,
                new Email(email),
                new PhoneNumber(phoneNumber));
    }
}
