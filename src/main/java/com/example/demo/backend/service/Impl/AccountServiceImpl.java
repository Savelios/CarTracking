package com.example.demo.backend.service.Impl;

import com.example.demo.backend.domain.User;
import com.example.demo.backend.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public org.springframework.security.core.userdetails.User authenticate(String username, String password) throws Exception {
        var userOptional = repository.findFirstByUsername(username);
        if (userOptional.isEmpty())
            throw new Exception("Такого пользователя нет!");

        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new Exception("Такого пользователя нет!");

        var u = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().getRole()))
        );
        SecurityContext context = SecurityContextHolder.getContext();
        var authentication = new UsernamePasswordAuthenticationToken(
                u.getUsername(),
                u.getPassword(),
                u.getAuthorities()
        );
        context.setAuthentication(authentication);

        return u;
    }
}
