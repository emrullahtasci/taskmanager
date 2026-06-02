package com.example.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class GiftItemRequest {

    @NotBlank(message = "Urun adi bos olamaz")
    private String name;
    private String description;
    @Positive(message = "Fiyat pozitif olmalidir")
    private double price;
    private String supplierName;
    private String customerGroup;
    private boolean inStock;

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