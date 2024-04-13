package monkeyforest.blog.domain.post.service;

import monkeyforest.blog.domain.post.persistence.entity.Post;
import monkeyforest.blog.domain.post.persistence.repository.PostRepository;
import monkeyforest.blog.domain.post.service.parameters.CreatePostParameters;
import monkeyforest.blog.domain.post.service.parameters.UpdatePostParameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게시글 수정 동시성 테스트(낙관적 락)")
@SpringBootTest
class PostOptimisticLockingTest {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @Test
    void test() throws InterruptedException {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setQueueCapacity(20);
        executor.initialize();

        Post post = createPost();
        var countDownLatch = new CountDownLatch(3);
        AtomicInteger number = new AtomicInteger(2);
        AtomicInteger failureCount = new AtomicInteger();
        AtomicReference<Exception> exception = new AtomicReference<>(new Exception());
        for (int i = 0; i < 3; i++) {
            executor.execute(() -> {
                try {
                    postService.updatePost(
                            new UpdatePostParameters(post.getId(), String.format("title%d", number.getAndIncrement()), String.format("body2%d", number.getAndIncrement()), post.getVersion()));
                } catch (Exception e) {
                    failureCount.getAndIncrement();
                    exception.set(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        assertThat(failureCount.get()).isEqualTo(2);
        assertThat(exception.get()).isInstanceOf(ObjectOptimisticLockingFailureException.class);
    }

    private Post createPost() {
        return postRepository.save(postService.createPost(new CreatePostParameters("title1", "body1", "writer")));
    }

}