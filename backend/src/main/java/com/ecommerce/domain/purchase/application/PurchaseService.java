package com.ecommerce.domain.purchase.application;

import com.ecommerce.domain.item.domain.ItemRepository;
import com.ecommerce.domain.purchase.domain.Purchase;
import com.ecommerce.domain.purchase.domain.PurchaseInfo;
import com.ecommerce.domain.item.application.ItemService;
import com.ecommerce.domain.purchase.domain.PurchaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    public final Logger logger = LoggerFactory.getLogger(getClass());

    private PurchaseRepository purchaseRepository;
    private ItemRepository itemRepository;

    public PurchaseService(final PurchaseRepository purchaseRepository, final ItemRepository itemRepository) {
        this.purchaseRepository = purchaseRepository;
        this.itemRepository = itemRepository;
    }

    public List<Purchase> list() {
//        return this.purchaseRepository.list();
        return null;
    }

    public Purchase get(long id) {

//        return this.purchaseRepository.get(id);
        return null;
    }

    public Purchase getByPurchaseId(int pid) {
//        return this.purchaseRepository.getByPurchaseId(pid);
        return null;
    }

    /**
     * TODO Sub PJT Ⅲ 과제 3
     * 판매자 관련 Purchase 조회
     */
    public List<PurchaseInfo> getBySeller(int id) {
        return null;
    }

    /**
     * TODO Sub PJT Ⅲ 과제 3
     * 구매자 관련 Purchase 조회
     */
    public List<PurchaseInfo> getByBuyer(int id) {
        return null;
    }

    /**
     * TODO Sub PJT Ⅲ 과제 3
     * Purchase 정보 등록
     *
     * @param purchase
     * @return
     */
    public Purchase create(final Purchase purchase) {
        return null;
    }

    /**
     * TODO Sub PJT Ⅲ 과제 3
     * Purchase 상태 업데이트
     *
     * @return
     */
    public Purchase updateState(int pid, String state) {
        return null;
    }

}
