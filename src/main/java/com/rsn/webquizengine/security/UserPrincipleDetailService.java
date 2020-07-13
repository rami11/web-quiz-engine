package com.rsn.webquizengine.security;

import com.rsn.webquizengine.user.User;
import com.rsn.webquizengine.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPrincipleDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserPrincipleDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = this.userRepository.findByEmail(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("Username does not exist");
        }
        UserPrinciple userPrinciple = new UserPrinciple(userOpt.get());
        return userPrinciple;
    }
}
