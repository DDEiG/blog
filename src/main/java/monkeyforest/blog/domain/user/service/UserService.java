package monkeyforest.blog.domain.user.service;

import lombok.RequiredArgsConstructor;
import monkeyforest.blog.domain.user.persistence.entity.User;
import monkeyforest.blog.domain.user.persistence.repository.UserRepository;
import monkeyforest.blog.domain.user.service.parameters.CreateUserParameters;
import monkeyforest.blog.domain.user.service.parameters.LoginUserParameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

//    public Page<User> getUsers(Pageable pageable) {
//        return userRepository.findAll(pageable);
//    }

    public User createUser(CreateUserParameters parameters) {
        String encodedPassword = passwordEncoder.encode(parameters.password());
        User user = User.createUser(
                parameters.userName(),
                encodedPassword,
                parameters.gender(),
                parameters.birthday(),
                parameters.email(),
                parameters.phoneNumber());
        return userRepository.save(user);
    }
//
//    public void findUserByUsername(LoginUserParameters parameters) {
//        User user = userRepository.findByEmail(parameters.email())
//                .orElseThrow(() -> new UsernameNotFoundException("Email Not Exist."));
//        String encodedPassword = passwordEncoder.encode(parameters.password());
//        if (!user.getPassword().equals(encodedPassword)) {
//            throw new RuntimeException("Password Not Match.");
//        }
//    }
}
