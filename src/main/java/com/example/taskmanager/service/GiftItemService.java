package com.example.taskmanager.service;

import com.example.taskmanager.entity.GiftItem;
import com.example.taskmanager.dto.GiftItemRequest;
import com.example.taskmanager.repository.GiftItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GiftItemService {

    private final GiftItemRepository giftItemRepository;

    @Autowired
    public GiftItemService(GiftItemRepository giftItemRepository) {
        this.giftItemRepository = giftItemRepository;
    }

    public List<GiftItem> getAllGifts() {
        return giftItemRepository.findAll();
    }

    public Optional<GiftItem> getGiftById(Long id) {
        return giftItemRepository.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public GiftItem createGift(GiftItemRequest request) {
        GiftItem gift = new GiftItem();
        gift.setName(request.getName());
        gift.setDescription(request.getDescription());
        gift.setPrice(request.getPrice());
        gift.setSupplierName(request.getSupplierName());
        gift.setCustomerGroup(request.getCustomerGroup());
        gift.setInStock(request.isInStock());
        return giftItemRepository.save(gift);
    }

    @Transactional(rollbackFor = Exception.class)
    public Optional<GiftItem> updateGift(Long id, GiftItemRequest request) {
        return giftItemRepository.findById(id).map(existingGift -> {
            existingGift.setName(request.getName());
            existingGift.setDescription(request.getDescription());
            existingGift.setPrice(request.getPrice());
            existingGift.setSupplierName(request.getSupplierName());
            existingGift.setCustomerGroup(request.getCustomerGroup());
            existingGift.setInStock(request.isInStock());
            return giftItemRepository.save(existingGift);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteGift(Long id) {
        if (giftItemRepository.existsById(id)) {
            giftItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<GiftItem> getFilterByPrice(double price) {
        return giftItemRepository.findByPriceLessThanEqual(price);
    }

    public List<GiftItem> getByCustomerGroup(String group) {
        return giftItemRepository.findByCustomerGroupIgnoreCase(group);
    }
}