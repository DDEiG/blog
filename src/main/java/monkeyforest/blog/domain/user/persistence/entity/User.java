package monkeyforest.blog.domain.user.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import monkeyforest.blog.domain.BaseDateTimes;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class User extends BaseDateTimes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private UserName userName;
    @Column(nullable = false)
    private Email email;
    @Column(nullable = false)
    private Gender gender;
    @Column(nullable = false)
    private LocalDate birthday;
    @Column(nullable = false)
    private PhoneNumber phoneNumber;
}
