package monkeyforest.blog.web.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.post.entity.Post;
import monkeyforest.blog.domain.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public String findPosts(@RequestParam(defaultValue = "0") @Min(0) int pageNumber,
                            @RequestParam(defaultValue = "10") @Min(1) int pageSize,
                            Model model) {
        Page<Post> postPage = postService.findPosts(pageNumber, pageSize);
        model.addAttribute("postPage", postPage);
        return "posts";
    }

    @GetMapping("/posts/search")
    public String searchPosts(@RequestParam String titleForSearch,
                              @RequestParam(defaultValue = "0") @Min(0) int pageNumber,
                              @RequestParam(defaultValue = "10") @Min(1) int pageSize,
                              Model model) {
        Page<Post> postPage = postService.searchPosts(titleForSearch, pageNumber, pageSize);
        model.addAttribute("postPage", postPage);
        return "posts";
    }

    @GetMapping("/posts/{id}")
    public String findPost(@PathVariable Long id, Model model) {
        Post post = postService.findPost(id);
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/post/write")
    public String writePost() {
        return "post-write";
    }

    @PostMapping("/post/write")
    public String writePost(@RequestParam String title, @RequestParam String body) {
        Post post = postService.createPost(title, body);
        return "redirect:/posts/" + post.getId();
    }
}
