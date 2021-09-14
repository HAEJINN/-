package com.ecommerce.domain.item.api;

import com.ecommerce.domain.item.application.ItemService;
import com.ecommerce.domain.item.domain.Item;
import com.ecommerce.domain.item.dto.ItemDeleteResponse;
import com.ecommerce.domain.item.dto.ItemFindListResponse;
import com.ecommerce.domain.item.dto.ItemFindResponse;
import com.ecommerce.domain.item.dto.ItemFindSellerListResponse;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ItemController {
    public final Logger logger = LoggerFactory.getLogger(getClass());

    private final ItemService itemService;

    public ItemController(final ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * TODO Sub PJT Ⅲ 과제 3
     * 상품 등록
     *
     * @param item
     * @return Item
     */
    @ApiOperation("Register an item")
    @PostMapping("/items")
    public Item save(@RequestBody Item item) {
        return itemService.save(item);
    }

    @ApiOperation("Fetch all items")
    @GetMapping( "/items")
    public ResponseEntity<List<ItemFindListResponse>> findAll() {
        final List<Item> items = itemService.findAll();
        final List<ItemFindListResponse> itemFindListResponses = items.stream()
                .map(ItemFindListResponse::ofItem)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(itemFindListResponses);
    }

    @ApiOperation("Fetch an item with id")
    @GetMapping("/items/{id}")
    public ResponseEntity<ItemFindResponse> findById(@PathVariable final Long id) {
        final Item item = itemService.findById(id);
        final ItemFindResponse itemFindResponse = ItemFindResponse.ofItem(item);
        return ResponseEntity.ok().body(itemFindResponse);
    }

    @ApiOperation("Fetch an item with id")
    @GetMapping("/items/of/{userId}")
    public ResponseEntity<List<ItemFindSellerListResponse>> findBySeller(@PathVariable final Long userId) {
        final List<Item> items = itemService.findBySeller(userId);
        final List<ItemFindSellerListResponse> itemFindSellerListResponses = items.stream()
                .map(ItemFindSellerListResponse::ofItem)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(itemFindSellerListResponses);
    }

    /**
     * TODO Sub PJT Ⅲ 과제 3
     * 상품 판매 취소
     *
     * @param id 아이템 id
     * @return Item
     */
    @ApiOperation("Delete an item with id")
    @DeleteMapping("/items/{id}")
    public ResponseEntity<ItemDeleteResponse> delete(@PathVariable final Long id) {
        final Item item = itemService.delete(id);
        final ItemDeleteResponse itemDeleteResponse = ItemDeleteResponse.ofItem(item);
        return ResponseEntity.ok().body(itemDeleteResponse);
    }

}
