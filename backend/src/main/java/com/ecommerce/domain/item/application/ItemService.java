package com.ecommerce.domain.item.application;

import com.ecommerce.domain.exception.ApplicationException;
import com.ecommerce.domain.item.domain.Item;
import com.ecommerce.domain.item.domain.ItemRepository;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    public final Logger logger = LoggerFactory.getLogger(getClass());

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public ItemService(final ItemRepository itemRepository, final UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(final Long id) {
        return itemRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Item> findBySeller(final Long userId) {
        final User user = findUserById(userId);
        return itemRepository.findBySeller(user);
    }

    /**
     * TODO Sub PJT Ⅲ 과제 3
     * 상품 등록 시 상품 정보를 저장한다.
     *
     * @param item
     * @return Item
     */
    public Item save(final Item item) {
        return itemRepository.save(item);
    }

    /**
     * TODO Sub PJT Ⅲ 과제 3
     * 상품 판매 취소
     *
     * @param id 상품 id
     * @return Item
     */
    public Item delete(final Long id) {
        final Item item = findItemById(id);
        itemRepository.delete(item);
        return item;
    }

    private User findUserById(final Long id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    private Item findItemById(final Long id) {
        return itemRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    /**
     * 상품 정보 업데이트
     *
     * @param item
     * @return
     */
    public Item update(final Item item) {
        final Item findItem = findItemById(item.getId());
        return findItem.update(item);
    }

}
