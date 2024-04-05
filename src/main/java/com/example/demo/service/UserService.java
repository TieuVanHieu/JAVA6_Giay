package com.example.demo.service;


import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.demo.model.AccountEntity;
import com.example.demo.repository.AccountEntityDAO;
@Service
@Transactional
public class UserService implements UserDetailsService{
    @Autowired
    AccountEntityDAO accountDAO;
    @Autowired
    BCryptPasswordEncoder pe;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AccountEntity account = accountDAO.findById(username).get();
            String password = account.getPassWord();
            String[] roles = account.getAuthorities().stream()
                    .map(au -> au.getRole().getRoleId())
                    .collect(Collectors.toList()).toArray(new String[0]);
            return User.withUsername(username)
                    .password(pe.encode(password))
                    .roles(roles).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(username + "not found");
        }
    }

    public void loginFormOAuth2(OAuth2AuthenticationToken oauth2) {
        String email = oauth2.getPrincipal().getAttribute("email");
        String password = Long.toHexString(System.currentTimeMillis());
        System.out.println(email);
        String name = oauth2.getPrincipal().getAttribute("name");
        System.out.println(name);
        UserDetails user = User
        .withUsername(email)
        .password(pe.encode(password))
        .roles("GUEST").build();

        Authentication auth = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
