package monkeyforest.blog.web.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.post.entity.Post;
import monkeyforest.blog.domain.post.service.PostService;
import monkeyforest.blog.web.controller.form.PostCreateForm;
import monkeyforest.blog.web.controller.form.PostUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("post", new PostCreateForm());
        return "post-write";
    }

    @PostMapping("/post/write")
    public String writePost(@ModelAttribute("post") @Valid PostCreateForm postCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post-write";
        }
        postService.createPost(postCreateForm.toPost());
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String postEdit(@PathVariable Long id, Model model) {
        Post post = postService.findPost(id);
        model.addAttribute("post", PostUpdateForm.from(post));
        return "post-edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute("post") @Valid PostUpdateForm postUpdateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post-edit";
        }
        Post post = postService.updatePost(postUpdateForm.getId(), postUpdateForm.getTitle(), postUpdateForm.getBody());
        return "redirect:/posts/" + post.getId();
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
