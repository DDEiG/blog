package monkeyforest.blog.web.controller;

import monkeyforest.blog.domain.post.persistence.entity.Post;
import monkeyforest.blog.domain.post.persistence.repository.PostRepository;
import monkeyforest.blog.domain.post.service.PostService;
import monkeyforest.blog.web.controller.form.EditMode;
import monkeyforest.blog.web.controller.form.PostCreateForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    PostService postService;
    @MockBean
    PostRepository postRepository;

    @Test
    void testPostsWithTitle() throws Exception {
        given(postService.searchPosts(any(), any()))
                .willReturn(Page.empty());
        mockMvc.perform(get("/posts")
                        .param("title", "post1"))
                .andDo(print()) // TODO: 삭제
                .andExpect(status().isOk())
                .andExpect(view().name("post/list"));
    }

    @Test
    void testPostsWithoutTitle() throws Exception {
        given(postService.findPosts(any()))
                .willReturn(Page.empty());
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(view().name("post/list"));
    }

    @Test
    void testPost() throws Exception {
        given(postService.findPost(any()))
                .willReturn(Post.builder().build());
        mockMvc.perform(get("/posts/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("post/detail"));
    }

    @Test
    void testPostWrite() throws Exception {
        mockMvc.perform(get("/post/write"))
                .andExpect(status().isOk())
                .andExpect(view().name("post/edit"))
                .andExpect(model().attribute("post", new PostCreateForm()))
                .andExpect(model().attribute("editMode", EditMode.CREATE));
    }

    @Test
    void testWritePost() throws Exception {
        var form = new PostCreateForm();
        form.setTitle("haha");
        form.setBody("hoho");
        mockMvc.perform(post("/post")
                        .flashAttr("post", form))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts"));
    }

    @Test
    void testWritePostWithBindingResult() throws Exception {
        var form = new PostCreateForm();
        form.setTitle("haha");
        mockMvc.perform(post("/post")
                        .flashAttr("post", form))
                .andExpect(status().isOk())
                .andExpect(view().name("post/edit"));
    }

}