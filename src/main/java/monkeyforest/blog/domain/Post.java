package monkeyforest.blog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private Long id;
    @Column(name = "postTitle", nullable = false, length = 50)
    private String title;
    @Column(name = "postBody", nullable = false, columnDefinition = "text")
    private String body;
}
