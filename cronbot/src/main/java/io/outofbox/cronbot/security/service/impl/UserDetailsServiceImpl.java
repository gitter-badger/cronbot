package io.outofbox.cronbot.security.service.impl;

import io.outofbox.cronbot.model.User;
import io.outofbox.cronbot.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ahelmy
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> account = userRepository.findByUsername(username);
        if(!account.isPresent()){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        User user = account.get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        return user.getRoles().stream().map(role->new SimpleGrantedAuthority("ROLE_"+role.getName())).collect(Collectors.toSet());
    }
}
