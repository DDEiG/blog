package monkeyforest.blog.web.controller;

import monkeyforest.blog.domain.post.persistence.repository.PostRepository;
import monkeyforest.blog.domain.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                .andExpect(status().isOk());
    }

    @Test
    void testPostsWithoutTitle() throws Exception {
        given(postService.findPosts(any()))
                .willReturn(Page.empty());
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk());
    }

}