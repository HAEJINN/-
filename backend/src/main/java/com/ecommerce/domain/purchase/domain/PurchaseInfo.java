package com.ecommerce.domain.purchase.domain;

import com.ecommerce.domain.item.domain.Item;
import com.ecommerce.domain.user.domain.User;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class PurchaseInfo extends Purchase {
    private Long id;
    private Long purchaseId; // 컨트랙트에서의 purchase ID
    private User seller;
    private User buyer;
    private Item item;
    private LocalDateTime createdAt;
    private String contractAddress;
    private String state = PurchaseState.INITIAL.toString(); // I(Initial-purchased), P(Paid), S(sent), C(confirmed), X(cancelled)

    private String itemName;
    private long itemId;
    private String image;

    public PurchaseInfo(Purchase p) {
        this.id = p.getId();
        this.purchaseId = p.getPurchaseId();
        this.state = p.getState();
        this.seller = p.getSeller();
        this.buyer = p.getBuyer();
        this.item = p.getItem();
        this.createdAt = p.getCreatedAt();
        this.contractAddress = p.getContractAddress();
    }

}
