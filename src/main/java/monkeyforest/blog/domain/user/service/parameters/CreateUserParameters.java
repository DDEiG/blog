package monkeyforest.blog.domain.user.service.parameters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import monkeyforest.blog.domain.user.persistence.entity.field.Email;
import monkeyforest.blog.domain.user.persistence.entity.field.Gender;
import monkeyforest.blog.domain.user.persistence.entity.field.PhoneNumber;
import monkeyforest.blog.domain.user.persistence.entity.field.UserName;

import java.time.LocalDate;

public record CreateUserParameters(Email username,String password, String nickname, Gender gender, LocalDate birthday) {
}
