package com.ecommerce.domain.purchase.domain;

import com.ecommerce.domain.item.domain.Item;
import com.ecommerce.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "purchases")
@Entity
public class Purchase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long purchaseId; // 컨트랙트에서의 purchase ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private LocalDateTime createdAt;
    private String contractAddress;
    private String state = PurchaseState.INITIAL.toString(); // I(Initial-purchased), P(Paid), S(sent), C(confirmed), X(cancelled)

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", purchaseId=" + purchaseId +
                ", seller=" + seller +
                ", buyer=" + buyer +
                ", item=" + item +
                ", createdAt=" + createdAt +
                ", contractAddress='" + contractAddress + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

}
