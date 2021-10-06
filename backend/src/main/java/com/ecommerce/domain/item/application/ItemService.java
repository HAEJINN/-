package com.ecommerce.domain.item.application;

import com.ecommerce.domain.follow.dto.FollowerListResponse;
import com.ecommerce.domain.item.domain.Item;
import com.ecommerce.domain.item.domain.ItemRepository;
import com.ecommerce.domain.item.dto.ItemListResponse;
import com.ecommerce.domain.user.domain.User;
import com.ecommerce.domain.user.domain.UserRepository;
import com.ecommerce.domain.user.dto.UserFindListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<ItemListResponse> findFour(){
        final PageRequest pageRequest = PageRequest.of(0, 4, Sort.Direction.DESC, "id");
        return itemRepository.findAll(pageRequest).stream()
                .map(ItemListResponse::ofItem)
                .collect(Collectors.toList());
    }

    public List<ItemListResponse> findRandom() {
        int count = 0;
        List<Item> itemList = itemRepository.findAll();
        List<ItemListResponse> items = new ArrayList<>();
        count = itemList.size();
        int repeat = count<12?count:12;
        for(int i=0; i<repeat; i++){
            int random = (int)(Math.random()*count);
            items.add(ItemListResponse.ofItem(itemList.get(random)));
        }
        return items;
    }

    public List<ItemListResponse> findByUserId(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            List<Item> items = itemRepository.findByUser(user.get());
            return items.stream()
                    .map(ItemListResponse::ofItem)
                    .collect(Collectors.toList());
        }
        return null;

    }

}
