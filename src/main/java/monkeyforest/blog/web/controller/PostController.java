package monkeyforest.blog.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.post.persistence.entity.Post;
import monkeyforest.blog.domain.post.persistence.repository.PostRepository;
import monkeyforest.blog.domain.post.service.PostService;
import monkeyforest.blog.web.controller.form.EditMode;
import monkeyforest.blog.web.controller.form.PostCreateForm;
import monkeyforest.blog.web.controller.form.PostModifyForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.springframework.util.StringUtils.hasText;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

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
        return "post/list";
    }

    @GetMapping("/posts/{id}")
    public String post(@PathVariable Long id, Model model) {
        Post post = postService.findPost(id);
        model.addAttribute("post", post);
        return "post/detail";
    }

    @GetMapping("/post/write")
    public String writePostForm(Model model) {
        model.addAttribute("post", new PostCreateForm());
        model.addAttribute("editMode", EditMode.CREATE);
        return "post/edit";
    }

    @PostMapping("/post")
    public String writePost(@ModelAttribute("post") @Valid PostCreateForm postCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/edit";
        }
        postService.createPost(postCreateForm.toParameters());
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/modify")
    public String modifyPostForm(@PathVariable Long id, Model model) {
        Post post = postService.findPost(id);
        model.addAttribute("post", PostModifyForm.from(post));
        model.addAttribute("editMode", EditMode.UPDATE);
        return "post/edit";
    }

    @PutMapping("/posts/{id}")
    public String modifyPost(@ModelAttribute("post") @Valid PostModifyForm postModifyForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/edit";
        }
        Post post = postService.updatePost(postModifyForm.toParameters());
        return "redirect:/posts/" + post.getId();
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        var post = postService.findPost(id);
        postRepository.delete(post);
        redirectAttributes.addFlashAttribute("deletedPostTitle", post.getTitle());
        return "redirect:/posts";
    }
}
