package com.ecommerce.domain.user.domain;

import com.ecommerce.domain.follow.domain.Follow;
import com.ecommerce.global.common.BaseTimeEntity;
import com.ecommerce.domain.photo.domain.Photo;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    @OneToMany(mappedBy = "follower" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> myFollowing = new HashSet<>();

    @OneToMany(mappedBy = "following" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> myFollower = new HashSet<>();

    @Builder
    public User(final String email, final String name, final String password, final UserStatus status,
                final Photo photo, final Set<Follow> myFollowing, final Set<Follow> myFollower) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.status = UserStatus.ACTIVITY;
        this.photo = photo;
        this.myFollowing = myFollowing;
        this.myFollower = myFollower;
    }

    public User changeEmail(final String email) {
        validateNull(email);
        this.email = email;
        return this;
    }

    public User changePhoto(final Photo photo) {
        validateNull(photo);
        this.photo = photo;
        return this;
    }

    private void validateNull(final Object object) {
        if(Objects.isNull(object)) {
            throw new IllegalArgumentException();
        }
    }

    public void passwordEncode(final PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void matchPassword(final PasswordEncoder passwordEncoder, final String password) {
        if(!passwordEncoder.matches(password, this.password)) {
            throw new IllegalArgumentException();
        }
    }

    public void addFollowing(final Follow follow) {
        myFollowing.add(follow);
        follow.changeFollower(this);
    }

    public void addFollower(final Follow follower) {
        myFollower.add(follower);
        follower.changeFollowing(this);
    }


}
