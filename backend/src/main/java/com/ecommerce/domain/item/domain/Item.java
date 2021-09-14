package com.ecommerce.domain.item.domain;

import com.ecommerce.domain.user.domain.User;
import lombok.*;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "items")
@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String explanation;
    private boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    private LocalDateTime registeredAt;
    private String image;

    @Builder
    public Item(final String name, final String category, final String explanation,
                final Boolean available, final User seller, final LocalDateTime registeredAt, final String image) {
        this.name = name;
        this.category = category;
        this.explanation = explanation;
        this.available = available;
        this.seller = seller;
        this.registeredAt = registeredAt;
        this.image = image;
    }

    public Item update(final Item other) {
        return changeName(other.name)
                .changeExplanation(other.explanation)
                .changeAvailable(other.available);
    }

    private Item changeName(final String name) {
        validateStringBlank(name);
        this.name = name;
        return this;
    }

    private Item changeExplanation(final String explanation) {
        validateStringBlank(explanation);
        this.explanation = explanation;
        return this;
    }

    private Item changeAvailable(final boolean available) {
        this.available = available;
        return this;
    }

    private void validateStringBlank(final String sentence) {
        if(Strings.isBlank(sentence)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return "{ id: " + id +
                "\n\tname: " + name +
                "\n\texplanation: " + explanation +
                "\n\tavailable: " + available +
                "\n\tseller: " + seller +
                "\n\tregisteredAt: " + registeredAt +
                "\n\timage: " + image +
                " }";
    }

}
