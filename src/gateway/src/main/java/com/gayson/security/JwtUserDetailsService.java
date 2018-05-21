package com.gayson.security;

import com.gayson.model.User;
import com.gayson.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String telephone) throws UsernameNotFoundException {
        User user = userRepository.findByTelephone(telephone);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("no such user with telephone %s.", telephone));
        }
        return JwtUser.create(user);
    }
}
