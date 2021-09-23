package com.ecommerce.domain.user.domain;

import com.ecommerce.domain.BaseTimeEntity;
import com.ecommerce.domain.photo.domain.Photo;
import com.ecommerce.domain.user.domain.vo.SocialType;
import com.ecommerce.domain.user.domain.vo.UserRole;
import com.ecommerce.domain.user.domain.vo.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @Column(name = "user_commnet")
    private String comment;

    @Column(name = "user_description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_social_type")
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus status;

    @Builder
    public User(final String name, final String email, final Photo photo,
                final String comment, final String description, final UserRole userRole,
                final SocialType socialType, final UserStatus status) {
        this.name = name;
        this.email = email;
        this.photo = photo;
        this.comment = comment;
        this.description = description;
        this.userRole = userRole;
        this.socialType = socialType;
        this.status = status;
    }

    public User update(final String name, final String email, final String photo, final SocialType socialType) {
        return changeName(name)
                .changeEmail(email)
                .changePhoto(photo)
                .changeSocialType(socialType);
    }

    public User changeName(final String name) {
        validateNull(photo);
        this.name = name;
        return this;
    }

    public User changeEmail(final String email) {
        validateNull(photo);
        this.email = email;
        return this;
    }

    public User changePhoto(final String photo) {
        validateNull(photo);
        this.photo = new Photo(photo);
        return this;
    }

    public User changeSocialType(final SocialType socialType) {
        validateNull(socialType);
        this.socialType = socialType;
        return this;
    }

    private void validateNull(final Object object) {
        if(Objects.isNull(object)) {
            throw new IllegalArgumentException();
        }
    }

    public String getUserRole() {
        return userRole.getKey();
    }

}
