package com.ecommerce.domain.auth.oauth2;

import com.ecommerce.domain.auth.session.SessionUser;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private static final String USER = "user";

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = oAuth2User(userRequest);
        String registrationId = registrationId(userRequest);
        String userNameAttributeName = userNameAttributeName(userRequest);
        OAuthAttribute attributes = oAuthAttribute(oAuth2User, registrationId, userNameAttributeName);
        User user = saveOrUpdate(attributes);
        registerSessionUser(user);
        return defaultOAuth2User(attributes, user);
    }

    private OAuth2User oAuth2User(OAuth2UserRequest userRequest) {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        return delegate.loadUser(userRequest);
    }

    private String registrationId(OAuth2UserRequest userRequest) {
        return userRequest.getClientRegistration().getRegistrationId();
    }

    private String userNameAttributeName(OAuth2UserRequest userRequest) {
        return userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
    }

    @NotNull
    private OAuthAttribute oAuthAttribute(OAuth2User oAuth2User, String registrationId, String userNameAttributeName) {
        return OAuthAttribution.of(registrationId, userNameAttributeName, oAuth2User.getAttributes())
                .orElseThrow(IllegalArgumentException::new);
    }

    private User saveOrUpdate(OAuthAttribute attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> updateProfile(attributes, entity))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }

    private User updateProfile(OAuthAttribute attributes, User entity) {
        return entity.update(attributes.getName(), attributes.getEmail(), attributes.getPicture(), attributes.getSocialType());
    }

    private DefaultOAuth2User defaultOAuth2User(OAuthAttribute attributes, User user) {
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getUserRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private void registerSessionUser(User user) {
        httpSession.setAttribute(USER, new SessionUser(user));
    }

}