package com.example.taskmanager.controller;

import com.example.taskmanager.entity.GiftItem;
import com.example.taskmanager.dto.GiftItemRequest;
import com.example.taskmanager.service.GiftItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/gifts")
public class GiftItemController {

    private final GiftItemService giftItemService;

    @Autowired
    public GiftItemController(GiftItemService giftItemService) {
        this.giftItemService = giftItemService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<GiftItem> getAllGifts() {
        return giftItemService.getAllGifts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GiftItem> getGiftById(@PathVariable Long id) {
        return giftItemService.getGiftById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GiftItem> createGift(@Valid @RequestBody GiftItemRequest request) {
        GiftItem createdGift = giftItemService.createGift(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGift);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GiftItem> updateGift(@PathVariable Long id, @Valid @RequestBody GiftItemRequest request) {
        return giftItemService.updateGift(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteGift(@PathVariable Long id) {
        return giftItemService.deleteGift(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/filter-price")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<GiftItem> filterByPrice(@RequestParam double price) {
        return giftItemService.getFilterByPrice(price);
    }

    @GetMapping("/customer-group/{group}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public List<GiftItem> filterByGroup(@PathVariable String group) {
        return giftItemService.getByCustomerGroup(group);
    }
}