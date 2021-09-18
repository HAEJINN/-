package com.ecommerce.domain.auth.oauth2;

import com.ecommerce.domain.auth.oauth2.Attributes.GoogleAttribute;
import com.ecommerce.domain.auth.oauth2.Attributes.KaKaoAttribute;
import com.ecommerce.domain.auth.oauth2.Attributes.NaverAttribute;

import java.util.Map;
import java.util.Optional;

public enum OAuthAttribution {

    NAVER("naver"),
    KAKAO("kakao"),
    GOOGLE("google");

    private static final String ID = "id";

    private final String vendor;

    OAuthAttribution(String vendor) {
        this.vendor = vendor;
    }

    public static Optional<OAuthAttribute> of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if (NAVER.vendor.equals(registrationId)) {
            return Optional.ofNullable(NaverAttribute.ofNaver(ID, attributes));
        }
        if (KAKAO.vendor.equals(registrationId)) {
            return Optional.ofNullable(KaKaoAttribute.ofKakao(ID, attributes));
        }
        if (GOOGLE.vendor.equals(registrationId)) {
            return Optional.ofNullable(GoogleAttribute.ofGoogle(userNameAttributeName, attributes));
        }
        return Optional.empty();
    }

}

