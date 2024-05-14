package monkeyforest.blog.web.controller.form;

import lombok.Getter;
import lombok.Setter;
import monkeyforest.blog.domain.user.persistence.entity.field.Email;
import monkeyforest.blog.domain.user.persistence.entity.field.PhoneNumber;
import monkeyforest.blog.domain.user.persistence.entity.field.UserName;
import monkeyforest.blog.domain.user.service.parameters.CreateUserParameters;
import monkeyforest.blog.domain.user.service.parameters.LoginUserParameters;

@Getter
@Setter
public class LoginFormData {
    private String username;
    private String password;

    public LoginUserParameters toParameters() {
        return new LoginUserParameters(new Email(username),
                password);
    }
}
