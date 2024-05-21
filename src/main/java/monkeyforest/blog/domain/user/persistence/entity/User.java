package monkeyforest.blog.domain.user.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import monkeyforest.blog.domain.BaseDateTimes;
import monkeyforest.blog.domain.user.persistence.entity.field.*;

import java.time.LocalDate;
import java.util.Set;

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
    private Email username;
    @NotNull
    private String password;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false)
    private LocalDate birthday;
    @ElementCollection(targetClass = UserRole.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles")
    @Column(name = "role")
    private Set<UserRole> roles;

    private User(Email username, String password, String nickname,Gender gender, LocalDate birthday, Set<UserRole> roles) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.roles = roles;
    }

    public static User createUser(Email username,
                                  String encodedPassword,
                                  String nickname,
                                  Gender gender,
                                  LocalDate birthday) {
        return new User(username,
                encodedPassword, nickname, gender, birthday, Set.of(UserRole.USER));
    }
    public static User createAdministrator(Email username,
                                           String encodedPassword,
                                           String nickname,
                                           Gender gender,
                                           LocalDate birthday) {
        return new User(username,
                encodedPassword, nickname, gender, birthday, Set.of(UserRole.USER, UserRole.ADMIN));
    }

}
