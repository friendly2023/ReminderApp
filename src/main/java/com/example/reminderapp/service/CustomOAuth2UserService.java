package com.example.reminderapp.service;

import com.example.reminderapp.entity.Role;
import com.example.reminderapp.entity.User;
import com.example.reminderapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        log.info("[CustomOAuth2UserService] Пользователь загружен из OAuth2 провайдера");

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        log.info("[CustomOAuth2UserService] Получены данные: email={}, name={}", email, name);

        if (email == null) {
            log.error("[CustomOAuth2UserService] Email пользователя из OAuth2 отсутствует!");
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }

        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            log.info("[CustomOAuth2UserService] Пользователь с email={} не найден в БД. Создаем нового.", email);
            user = new User(name, email, null, Role.USER);
            user = userRepository.save(user);
            log.info("[CustomOAuth2UserService] Новый пользователь сохранен: {}", user);
        } else {
            log.info("[CustomOAuth2UserService] Пользователь найден в БД: {}", user);
        }

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())),
                oAuth2User.getAttributes(),
                "email"
        );
    }
}