package monkeyforest.blog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class BaseDateTimes {
    @CreatedDate
    @Column(columnDefinition = "timestamp")
    private LocalDateTime createdAt; //
    @LastModifiedDate
    @Column(columnDefinition = "timestamp")
    private LocalDateTime lastModifiedAt;
}
