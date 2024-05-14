package monkeyforest.blog.domain.user.persistence.repository;

import monkeyforest.blog.domain.user.persistence.entity.User;
import monkeyforest.blog.domain.user.persistence.entity.field.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(Email email);
}
