package org.tbk.bigfive.demo;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.tbk.bigfive.model.UserRepository;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class FakeUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public FakeUserDetailsService(UserRepository userRepository) {
        this.userRepository = requireNonNull(userRepository);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final List<org.tbk.bigfive.model.User> byName = userRepository.findByName(username);

        return byName.stream().findFirst()
                .map(user -> toUserDetails(user))
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    private UserDetails toUserDetails(org.tbk.bigfive.model.User user) {
        final List<SimpleGrantedAuthority> authorities = user.getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(toList());
        
        return User.withUsername(user.getName())
                .password(user.getPassword())
                .authorities(authorities)
                .disabled(!user.isEnabled())
                .build();
    }
}
