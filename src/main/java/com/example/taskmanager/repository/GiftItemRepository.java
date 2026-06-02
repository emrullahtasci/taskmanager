package com.example.taskmanager.repository;

import com.example.taskmanager.entity.GiftItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GiftItemRepository extends JpaRepository<GiftItem, Long> {
    List<GiftItem> findByPriceLessThanEqual(double price);
    List<GiftItem> findByCustomerGroupIgnoreCase(String customerGroup);
    List<GiftItem> findByNameContainingIgnoreCase(String name);
}