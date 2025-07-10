package com.example.reminderapp.service;

import com.example.reminderapp.entity.Role;
import com.example.reminderapp.entity.User;
import com.example.reminderapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    private final UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        log.debug("Логирование User старотовало");

        OidcUserService delegate = new OidcUserService();
        OidcUser oidcUser = delegate.loadUser(userRequest);

        String email = oidcUser.getEmail();
        String name = oidcUser.getFullName();

        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User(name, email, null, Role.USER);
            log.info("Новый User зарегистрирован");
            return userRepository.save(newUser);
        });

        OidcUser oidcUserResponse = new DefaultOidcUser(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole())),
                oidcUser.getIdToken(),
                oidcUser.getUserInfo()
        );

        log.info("User авторезирован из БД");
        return oidcUserResponse;
    }
}