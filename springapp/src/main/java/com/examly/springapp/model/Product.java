package com.examly.springapp.model;



import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
// Specify the name of the table in the database
@Table(name = "product")
public class Product {
    // Mark this field as the primary key
    @Id
    // Generate the primary key value automatically
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String photoImage;
    // @Column(name = "created_at", nullable = false)
    // private LocalDate createdAt;

    // @Column(name = "updated_at", nullable = false)
    // private LocalDate updatedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @ManyToOne
    // @JoinColumn(name="userId")
    private User user;
    //Specifies the setters and getters
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
    //     this.createdAt = createdAt;
    // }
    // public LocalDate getUpdatedAt() {
    //     return updatedAt;
    // }
    // public void setUpdatedAt(LocalDate updatedAt) {
    //     this.updatedAt = updatedAt;
    // }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}