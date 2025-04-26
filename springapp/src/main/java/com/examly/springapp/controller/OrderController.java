package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Order;
import com.examly.springapp.model.Order.OrderStatus;
import com.examly.springapp.service.OrderServiceImpl;
import com.examly.springapp.dto.OrderDTO;


import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/orders")
public class OrderController {
   @Autowired
   OrderServiceImpl service;
    // Constructor injection of the service
    // public OrderController(OrderServiceImpl service) {
    //     this.service = service;
    // }
    // Handles POST requests to add a new order
    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        order = service.addOrder(order);
        return ResponseEntity.status(200).body(order);
    }
    // Handles GET requests to fetch all orders
    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<Order> list = service.getAllOrders();
        return ResponseEntity.status(200).body(list);
    }
    // Handles GET requests to fetch a order by its ID
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        Order order = service.getOrderById(orderId);
        return ResponseEntity.status(200).body(order);
    }
    // Handles DELETE requests to remove a order by its ID
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        if(service.deleteOrder(orderId))
            return ResponseEntity.status(200).body("Deleted Successfully!!");
        return ResponseEntity.status(404).body("Not deleted!!");
    }
    @PutMapping("/{orderId}")
    public ResponseEntity<?>updateOrder(@PathVariable Long orderId, @RequestBody Order order){
        order=service.updateOrder(orderId,order);
        return ResponseEntity.status(200).body(order);

    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Long userId) {
        List<Order>list= service.getOrdersByUserId(userId);
        return ResponseEntity.status(200).body(list);
    }
}





// @RestController
// @RequestMapping("/orders")
// public class OrderController {

//     @Autowired
//     private OrderServiceImpl orderService;

//     @PostMapping
//     @PreAuthorize("hasRole('USER')")
//     public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) {
//         return ResponseEntity.ok(orderService.addOrder(orderDTO));
//     }

//     @GetMapping
//     @PreAuthorize("hasRole('ADMIN')")
//     public ResponseEntity<List<OrderDTO>> getAllOrders() {
//         return ResponseEntity.ok(orderService.getAllOrders());
//     }

//     @GetMapping("/{id}")
//     @PreAuthorize("hasAnyRole('ADMIN','USER')")
//     public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
//         return ResponseEntity.ok(orderService.getOrderById(id));
//     }

//     @GetMapping("/user/{userId}")
//     @PreAuthorize("hasRole('USER')")
//     public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable Long userId) {
//         return ResponseEntity.ok(orderService.getOrdersByUser(userId));
//     }

//     @PutMapping("/{id}")
//     @PreAuthorize("hasRole('ADMIN')")
//     public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
//         return ResponseEntity.ok(orderService.updateOrder(id, orderDTO));
//     }

//     @DeleteMapping("/{id}")
//     @PreAuthorize("hasRole('ADMIN')")
//     public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
//         orderService.deleteOrder(id);
//         return ResponseEntity.noContent().build();
//     }
// }