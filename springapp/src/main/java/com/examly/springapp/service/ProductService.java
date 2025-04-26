package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.dto.ProductDTO;
import com.examly.springapp.model.Product;

import jakarta.validation.Valid;

// Interface defining the contract for Product service operations
public interface ProductService {
    public @Valid Product addProduct(ProductDTO productDTO);
    public List<ProductDTO> getAllProducts();
    public Product getProductById(Long productId);
    public List<Product> getProductsByCategory(String category);
    public List<ProductDTO> getProductsByUserId(Long userId);
    public Product updateProduct(Long productId, Product product);
    public boolean deleteProduct(Long productId);
}