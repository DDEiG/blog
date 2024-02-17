package monkeyforest.blog.domain.post;

import jakarta.persistence.*;
import lombok.*;
import monkeyforest.blog.domain.BaseDateTimes;

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

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public void update(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
