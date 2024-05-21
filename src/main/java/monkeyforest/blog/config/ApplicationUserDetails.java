package monkeyforest.blog.config;

import monkeyforest.blog.domain.user.persistence.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationUserDetails implements UserDetails {
    private final Long id;
    private final String username;
    private final String displayName;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    public ApplicationUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername().toString();
        this.password = user.getPassword();
        this.displayName = user.getNickname();
        this.authorities = user.getRoles().stream()
                                .map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.name()))
                                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
