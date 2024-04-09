package monkeyforest.blog.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;
import monkeyforest.blog.domain.BaseDateTimes;
import org.springframework.util.Assert;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseDateTimes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, columnDefinition = "text")
    private String body;
    @Column(nullable = false, length = 30)
    private String writer;

    public Post(String title, String body, String writer) {
        Assert.hasText(title, "'title' must not be empty");
        Assert.hasText(body, "'body' must not be empty");
        Assert.hasText(writer, "'writer' must not be empty");
        this.title = title;
        this.body = body;
        this.writer = writer;
    }

    public void update(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
