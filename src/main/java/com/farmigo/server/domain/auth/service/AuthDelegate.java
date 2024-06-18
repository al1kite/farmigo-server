package com.farmigo.server.domain.auth.service;

import com.farmigo.server.domain.auth.model.enumeration.Role;
import com.farmigo.server.domain.auth.repository.UserRepository;
import com.farmigo.server.global.model.mongo.user.USER_INFO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthDelegate implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        USER_INFO userInfo = userRepository.findUserByEmail(userId);
        Role role = userInfo.getRole();
        userInfo.setAuthorities(Collections.singleton(new SimpleGrantedAuthority(role.getValue())));
        return userInfo;
    }
}
