package monkeyforest.blog.domain.user.service.parameters;

import monkeyforest.blog.domain.user.persistence.entity.field.Email;

public record LoginUserParameters(Email username, String password) {
}
