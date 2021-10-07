package com.ecommerce.domain.user.domain;

import com.ecommerce.domain.follow.domain.Follow;
import com.ecommerce.domain.wallet.domain.Wallet;
import com.ecommerce.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
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

    @Column(name = "user_profileImage")
    private String profileImage;

    @Column(name = "user_description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus status;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Wallet wallet;

    @OneToMany(mappedBy = "follower" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> myFollowing = new HashSet<>();

    @OneToMany(mappedBy = "following" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> myFollower = new HashSet<>();

    @Builder
    public User(final String email, final String name, final String password, final String profileImage, final String description,
                final Wallet wallet, final Set<Follow> myFollowing, final Set<Follow> myFollower) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.status = UserStatus.ACTIVITY;
        this.description = description;
        this.profileImage = profileImage;
        this.wallet = wallet;
        this.myFollowing = myFollowing;
        this.myFollower = myFollower;
    }

    public User changeEmail(final String email) {
        validateNull(email);
        this.email = email;
        return this;
    }

    public User changeProfileImage(final String profileImage) {
        validateNull(profileImage);
        this.profileImage = profileImage;
        return this;
    }

    public User changeDescription(final String description) {
        this.description = description;
        return this;
    }

    public User changeWallet(final Wallet wallet) {
        validateNull(wallet);
        this.wallet = wallet;
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
