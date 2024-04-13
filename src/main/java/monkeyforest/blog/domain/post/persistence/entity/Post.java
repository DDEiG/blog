package monkeyforest.blog.domain.post.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import monkeyforest.blog.domain.BaseDateTimes;
import org.springframework.util.Assert;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
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
    @Version
    private Long version;

    public Post(String title, String body, String writer) {
        Assert.hasText(title, "'title' must not be empty");
        Assert.hasText(body, "'body' must not be empty");
        Assert.hasText(writer, "'writer' must not be empty");
        this.title = title;
        this.body = body;
        this.writer = writer;
    }

    public void update(String title, String body, Long version) {
        Assert.hasText(title, "'title' must not be empty");
        Assert.hasText(body, "'body' must not be empty");
        Assert.notNull(version, "'body' must not be null");
        this.title = title;
        this.body = body;
        this.version = version;
    }
}
