package monkeyforest.blog.config;

import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.user.persistence.entity.User;
import monkeyforest.blog.domain.user.persistence.entity.field.Email;
import monkeyforest.blog.domain.user.persistence.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DatabaseUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(new Email(username))
                .orElseThrow(() -> new UsernameNotFoundException(
                        format("User with email %s could not be found", username)));

        return new ApplicationUserDetails(user);
    }
}
