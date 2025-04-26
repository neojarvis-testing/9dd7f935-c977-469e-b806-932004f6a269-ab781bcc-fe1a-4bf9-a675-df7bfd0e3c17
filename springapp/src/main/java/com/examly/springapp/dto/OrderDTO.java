package com.examly.springapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * OrderDTO represents the data transfer object for orders.
 * It carries order-related data between the client and server,
 * including shipping address, product list, user ID, and system-managed timestamps.
 */
@Data
public class OrderDTO {

    // Unique identifier for the order.
    // This field may be provided by the system rather than the client.
    private Long orderId;

    // Shipping address for the order delivery.
    // This field is required and must not exceed 255 characters.
    @NotBlank(message = "Shipping address cannot be blank")
    @Size(max = 255, message = "Shipping address cannot exceed 255 characters")
    private String shippingAddress;

    // List of product IDs included in the order.
    // This list must not be null.
    @NotNull(message = "Product List cannot be null")
    private List<Long> productList;

    // Identifier for the user placing the order.
    // This field is mandatory.
    @NotNull(message = "UserId cannot be null.")
    private Long userId;

    // The creation date managed by the system.
    // Clients are not required to provide this value.
    @Null(message = "Date not required to give.")
    private LocalDate createdAt;
 
    // The update date managed by the system.
    // Clients should leave this field null.
    @Null(message = "Date not required to give.")
    private LocalDate updatedAt;
}
