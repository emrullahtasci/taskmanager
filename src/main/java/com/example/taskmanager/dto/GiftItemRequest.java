package com.example.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;

public class GiftItemRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    private String description;
    private Boolean completed;

    public GiftItemRequest() {
    }

    public GiftItemRequest(String title, String description, Boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}