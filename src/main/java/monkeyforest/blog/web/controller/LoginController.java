package monkeyforest.blog.web.controller;

import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.user.persistence.entity.User;
import monkeyforest.blog.domain.user.service.UserService;
import monkeyforest.blog.web.controller.form.CreateUserFormData;
import monkeyforest.blog.web.controller.form.LoginFormData;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            model.addAttribute("login", new LoginFormData());
            return "user/login";
        } else {
            return "redirect:/";
        }
    }
//
//    @PostMapping("/login")
//    public String login(@ModelAttribute("login") LoginFormData loginFormData,
//                        BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            return "user/login";
//        }
//
//        userService.findUserByUsername(loginFormData.toParameters());
//
//        return "redirect:/";
//    }
}
