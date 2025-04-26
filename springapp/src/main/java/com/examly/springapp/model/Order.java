package com.examly.springapp.model;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Null;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Product> products;
    private String shippingAddress ;
    private double totalAmount;
    private int quantity;
    // @Column(name = "created_at", nullable = false)
    // private LocalDate createdAt;
 
    // @Column(name = "updated_at", nullable = false)
    // private LocalDate updatedAt;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    public enum OrderStatus{
        PENDING,
        SHIPPED
    }
    
    public long getOrderId() {
        return orderId;
    }
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
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

}

    
