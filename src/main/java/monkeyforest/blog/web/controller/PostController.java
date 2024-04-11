package monkeyforest.blog.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.post.entity.Post;
import monkeyforest.blog.domain.post.service.PostService;
import monkeyforest.blog.web.controller.form.PostCreateForm;
import monkeyforest.blog.web.controller.form.PostUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.StringUtils.hasText;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public String posts(@RequestParam(defaultValue = "") String title,
                        @PageableDefault @SortDefault.SortDefaults({@SortDefault(value = "id", direction = Sort.Direction.DESC)}) Pageable pageable,
                        Model model) {
        Page<Post> postPage;
        if (hasText(title)) {
            postPage = postService.searchPosts(title, pageable);
        } else {
            postPage = postService.findPosts(pageable);
        }
        model.addAttribute("postPage", postPage);
        model.addAttribute("title", title);
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
        postService.createPost(postCreateForm.toParameters());
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
