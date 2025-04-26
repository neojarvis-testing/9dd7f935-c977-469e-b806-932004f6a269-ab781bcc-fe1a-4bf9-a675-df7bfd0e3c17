package com.examly.springapp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;


public class ProductDTO {
    @NotNull
    private Long productId;

    // @NotBlank(message = "Name cannot be blank.") //Ensures name is not null or empty
    // @Size(max = 20, message = "Name must not exceed 20 characters.") //Limits description to 20 characters
    private String name;

    // @NotBlank(message = "Description cannot be blank.") //Ensures description is not null or empty
    // @Size(max = 100, message = "Description must not exceed 100 characters.") //Limits description to 100 characters
    private String description;

    // @NotNull(message = "Price cannot be null.") //Ensures price is not null or empty
    // @Positive(message = "Price must be greater than zero.") //Ensures price is not greater than zero by using positive validation
    private Double price;

    // @NotNull(message = "Stock cannot be null.") //Ensures stock is not null or empty
    // @Min(value = 0, message = "Stock cannot be negative.") //Ensures stock is not negative by using min validation
    private Integer stock;

    // @NotBlank(message = "Category cannot be blank.") //Ensures category is not null or empty
    // @Size(max = 20, message = "Category must not exceed 20 characters.") //Limits description to 20 characters
    private String category;

    @NotNull(message = "Photo URL cannot be blank.") //Ensures category is not null or empty
    @Size(max = 255, message = "Photo URL must not exceed 255 characters.") //Limits description to 255 characters
    private String photoImage;

    // @Null(message = "Date not required to give.")
    // private LocalDate createdAt;

    // @Null(message = "Date not required to give.")
    // private LocalDate updatedAt;

    @NotNull(message = "UserId cannot be null.") //Ensures userId cannot be null or empty
    private Long userId;

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhotoImage() {
        return photoImage;
    }

    public void setPhotoImage(String photoImage) {
        this.photoImage = photoImage;
    }

    // public LocalDate getCreatedAt() {
    //     return createdAt;
    // }

    // public void setCreatedAt(LocalDate createdAt) {
    //     LocalDate currentDate = LocalDate.now();
    //     this.createdAt = currentDate;
    // }

    // public LocalDate getUpdatedAt() {
    //     return updatedAt;
    // }

    // public void setUpdatedAt(LocalDate updatedAt) {
    //     LocalDate currentDate = LocalDate.now();
    //     this.updatedAt = currentDate;
    // }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}