package monkeyforest.blog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class WebSecurityConfig {
    // TODO: css 파일 권한 풀어줘야함. 적용안되고있음

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/post/write").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/post").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/posts/{id}/modify").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/posts/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/posts/{id}").hasRole("ADMIN")
                        .requestMatchers("/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/posts").permitAll()
                        .requestMatchers(HttpMethod.GET, "/posts/{id}").permitAll()
                        // TODO: 로그인을 해야 댓글을 달 수 있도록 권한설정(ROLE_USER)
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .permitAll())
                .logout(logout -> logout
                        .permitAll());
        return http.build();
    }
}
