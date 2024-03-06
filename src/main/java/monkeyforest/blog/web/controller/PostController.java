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
    public String posts(@RequestParam(defaultValue = "0") @Min(0) int pageNumber,
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
    public String post(@PathVariable Long id, Model model) {
        Post post = postService.findPost(id);
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/post/write")
    public String postWrite(Model model) {
        model.addAttribute(new Post());
        return "post-write";
    }

    @PostMapping("/post/write")
    public String writePost(@ModelAttribute Post post) {
        postService.createPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String postEdit(@PathVariable Long id, Model model) {
        Post post = postService.findPost(id);
        model.addAttribute("post", post);
        return "post-edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable Long id, @RequestParam String title, @RequestParam String body) {
        Post post = postService.updatePost(id, title, body);
        return "redirect:/posts/" + post.getId();
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
