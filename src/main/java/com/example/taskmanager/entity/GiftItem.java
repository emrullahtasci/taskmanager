package com.example.taskmanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "gift_items")
public class GiftItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Urun adi bos birakilamaz")
    private String name;

    private String description;

    @Positive(message = "Fiyat pozitif olmalidir")
    private double price;

    private String supplierName;

    private String customerGroup;

    private boolean inStock;

    public GiftItem() {}

    public GiftItem(String name, String description, double price, String supplierName, String customerGroup, boolean inStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.supplierName = supplierName;
        this.customerGroup = customerGroup;
        this.inStock = inStock;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    public String getCustomerGroup() { return customerGroup; }
    public void setCustomerGroup(String customerGroup) { this.customerGroup = customerGroup; }
    public boolean isInStock() { return inStock; }
    public void setInStock(boolean inStock) { this.inStock = inStock; }
}