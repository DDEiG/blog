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
    private UserName userName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false)
    private LocalDate birthday;
    @Column(nullable = false)
    private Email email;
    @Column(nullable = false)
    private PhoneNumber phoneNumber;
    @ElementCollection(targetClass = UserRole.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles")
    @Column(name = "role")
    private Set<UserRole> roles;
    @NotNull
    private String password;

    private User(Set<UserRole> roles, UserName userName, String password, Gender gender, LocalDate birthday, Email email, PhoneNumber phoneNumber) {
        this.roles = roles;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static User createUser(UserName userName,
                                  String encodedPassword,
                                  Gender gender,
                                  LocalDate birthday,
                                  Email email,
                                  PhoneNumber phoneNumber) {
        return new User(Set.of(UserRole.USER), userName,
                encodedPassword, gender, birthday, email, phoneNumber);
    }
    public static User createAdministrator(UserName userName,
                                           String encodedPassword,
                                           Gender gender,
                                           LocalDate birthday,
                                           Email email,
                                           PhoneNumber phoneNumber) {
        return new User(Set.of(UserRole.USER, UserRole.ADMIN), userName,
                encodedPassword, gender, birthday, email, phoneNumber);
    }

}
