package com.ecommerce.domain.item.application;

import com.ecommerce.domain.follow.dto.FollowerListResponse;
import com.ecommerce.domain.item.domain.Item;
import com.ecommerce.domain.item.domain.ItemRepository;
import com.ecommerce.domain.item.dto.ItemListResponse;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Transactional
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Transactional
    public void update(Item updateData, Long itemId) {
        final Item item = itemRepository.findById(itemId).orElseThrow(IllegalArgumentException::new);
        item.update(updateData);
    }

    @Transactional
    public void delete(Long itemId) {
        final Item item = itemRepository.findById(itemId).orElseThrow(IllegalArgumentException::new);
        itemRepository.delete(item);
    }

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(IllegalArgumentException::new);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<ItemListResponse> findByUserId(Long userId){
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        List<Item> items = itemRepository.findByUser(user);
        return items.stream()
                .map(ItemListResponse::ofItem)
                .collect(Collectors.toList());
    }

}
