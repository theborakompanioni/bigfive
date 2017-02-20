package org.tbk.bigfive.demo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.tbk.bigfive.model.UserRepository;

import java.util.List;

import static java.util.Objects.requireNonNull;

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
        return User.withUsername(user.getName())
                .password(user.getPassword())
                .authorities("create_goal", "edit_goal")
                .disabled(false)
                .build();
    }
}
