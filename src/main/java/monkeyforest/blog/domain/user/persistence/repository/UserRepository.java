package monkeyforest.blog.domain.user.persistence.repository;

import monkeyforest.blog.domain.user.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {
}
