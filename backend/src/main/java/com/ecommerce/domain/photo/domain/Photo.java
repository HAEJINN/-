package com.ecommerce.domain.photo.domain;

import com.ecommerce.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "photos")
@Entity
public class Photo extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @Column(name = "photo_path", nullable = false, unique = true)
    private String path;

    public Photo(final String path) {
        this.path = path;
    }

}
