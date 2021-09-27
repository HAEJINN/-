package com.ecommerce.domain.auth.oauth2.Attributes;

import com.ecommerce.domain.auth.oauth2.OAuthAttribute;
import com.ecommerce.domain.photo.domain.Photo;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.vo.SocialType;
import com.ecommerce.domain.user.domain.vo.UserRole;
import com.ecommerce.domain.user.domain.vo.UserStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class KaKaoAttribute implements OAuthAttribute {

    private static final String EMAIL = "email";
    private static final String NICKNAME = "nickname";
    private static final String PROFILE_IMAGE_URL = "profile_image_url";
    private static final String PROFILE = "profile";
    private static final String KAKAO_ACCOUNT = "kakao_account";

    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String picture;
    private final SocialType socialType;

    @Builder
    private KaKaoAttribute(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture, SocialType socialType) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.socialType = socialType;
    }

    public static OAuthAttribute ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get(KAKAO_ACCOUNT);
        Map<String, Object> profile = (Map<String, Object>) response.get(PROFILE);
        return KaKaoAttribute.builder()
                .name((String) profile.get(NICKNAME))
                .email((String) response.get(EMAIL))
                .picture((String) profile.get(PROFILE_IMAGE_URL))
                .socialType(SocialType.KAKAO)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    @Override
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .photo(new Photo(picture))
                .userRole(UserRole.USER)
                .status(UserStatus.ACTIVITY)
                .socialType(socialType)
                .build();
    }

}
