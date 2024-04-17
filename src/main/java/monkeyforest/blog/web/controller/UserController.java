package monkeyforest.blog.web.controller;

import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.user.persistence.entity.Gender;
import monkeyforest.blog.domain.user.persistence.repository.UserRepository;
import monkeyforest.blog.domain.user.service.UserService;
import monkeyforest.blog.web.controller.form.CreateUserFormData;
import monkeyforest.blog.web.controller.form.EditMode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    public String index(Model model,
                        @SortDefault.SortDefaults({
                                @SortDefault("userName.lastName"),
                                @SortDefault("userName.firstName")}) Pageable pageable) { //<.>
        model.addAttribute("users", userService.getUsers(pageable)); //<.>
        return "users/list";
    }

    @GetMapping("/create")
    @Secured("ROLE_USER")
    public String createUserForm(Model model) {
        model.addAttribute("user", new CreateUserFormData());
        model.addAttribute("genders", List.of(Gender.MALE, Gender.
                FEMALE, Gender.OTHER));
        model.addAttribute("editMode", EditMode.CREATE);
        return "users/edit";
    }

}
