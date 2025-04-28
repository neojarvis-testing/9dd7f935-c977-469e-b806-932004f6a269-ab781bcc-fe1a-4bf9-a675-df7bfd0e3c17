
package com.examly.springapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data  // Generates getters, setters, toString, equals, and hashCode
public class OrderDTO {
    private Long orderId;

    @NotBlank(message = "Shipping address cannot be blank")
    @Size(max = 255, message = "Shipping address cannot exceed 255 characters")
    private String shippingAddress;

    @NotNull(message = "Product list cannot be null")
    private List<Long> productList;

    @NotNull(message = "UserId cannot be null.") 
    private Long userId;

    @Null(message = "Date not required to give.")
    private LocalDate createdAt;

    @Null(message = "Date not required to give.")
    private LocalDate updatedAt;
}