package monkeyforest.blog.web.controller;

import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.user.persistence.entity.field.Gender;
import monkeyforest.blog.domain.user.service.UserService;
import monkeyforest.blog.web.controller.form.CreateUserFormData;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String createUserForm(Model model) {
        model.addAttribute("user", new CreateUserFormData());
        model.addAttribute("genders", List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER));
        return "user/edit";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") CreateUserFormData createUserFormData,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER));
            return "user/edit";
        }

        userService.createUser(createUserFormData.toParameters());

        return "redirect:/";
    }

}
