package com.ecommerce.domain.auth.oauth2;

import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.vo.SocialType;

import java.util.Map;

public interface OAuthAttribute {

    Map<String, Object> getAttributes();

    String getNameAttributeKey();

    String getName();

    String getEmail();

    String getPicture();

    SocialType getSocialType();

    User toEntity();

}
