package monkeyforest.blog.web.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.post.entity.Post;
import monkeyforest.blog.domain.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public String findPosts(@RequestParam(defaultValue = "0") @Min(0) int pageNumber,
                            @RequestParam(defaultValue = "10") @Min(1) int pageSize, Model model) {
        Page<Post> postPage = postService.find(pageNumber, pageSize);
        model.addAttribute("postPage", postPage);
        return "posts";
    }
}
