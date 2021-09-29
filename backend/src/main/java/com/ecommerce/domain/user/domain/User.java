package com.ecommerce.domain.user.domain;

import com.ecommerce.global.common.BaseTimeEntity;
import com.ecommerce.domain.photo.domain.Photo;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Table(name = "users")
@Entity
public class User extends BaseTimeEntity implements Serializable {

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

    protected User() {
    }

    @Builder
    public User(final String email, final String name, final String password,
                final UserStatus status, final Photo photo) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.photo = photo;
        this.status = UserStatus.ACTIVITY;
    }

    public User changeEmail(final String email) {
        validateNull(photo);
        this.email = email;
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
}
