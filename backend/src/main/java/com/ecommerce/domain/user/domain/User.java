package com.ecommerce.domain.user.domain;

import com.ecommerce.domain.exception.DomainException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Builder
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User update(final User other) {
        return changeName(other.name)
                .changePassword(other.password)
                .changeEmail(other.email);
    }

    public void matchPassword(final String password) {
        if (!this.password.equals(password)){
            throw new DomainException("비밀번호가 일치하지 않습니다.");
        }
    }

    public User changeName(final String name) {
        validateStringBlank(name);
        this.name = name;
        return this;
    }

    public User changeEmail(final String email) {
        validateStringBlank(email);
        this.email = email;
        return this;
    }

    public User changePassword(final String password) {
        validateStringBlank(password);
        this.password = password;
        return this;
    }

    private void validateStringBlank(final String sentence) {
        if (Strings.isBlank(sentence)) {
            throw new IllegalArgumentException();
        }
    }

}
