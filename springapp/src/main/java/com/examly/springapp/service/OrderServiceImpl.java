package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Order;
import com.examly.springapp.model.Order.OrderStatus;
import com.examly.springapp.model.Product;
import com.examly.springapp.model.User;

import com.examly.springapp.dto.OrderDTO;
import com.examly.springapp.exception.DataNotFoundException;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.OrderRepo;
import com.examly.springapp.repository.ProductRepo;
import com.examly.springapp.repository.UserRepo;
@Service
public class OrderServiceImpl {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    UserRepo userRepo;

     // Logger for tracking the application's flow
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    // Dependency injection for Feedback repository
    

    // Constructor for injecting dependencies
    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
       // Adds a new order by traversing the map in OrderDTO
    // public Order createOrder(OrderDTO orderDTO) {
    //     Order order = new Order();
    //     order.setShippingAddress(orderDTO.getShippingAddress());

    //     List<Long> productIdList = orderDTO.getProductList();
    //     double totalAmount = 0.0;
    //     int totalQuantity = 0;

    //     List<Product> productList = new ArrayList<>();

    //     // Consolidate quantities for duplicate product IDs
    //     for(long prod:productIdList) {
    //         Product product = productRepo.findById(prod).orElse(null);
    //         if(product!=null){
    //             int productQuantity=product.getStock()-1;
    //                if(productQuantity<0)
    //                     return null;
    //             product.setStock(productQuantity);
    //             productRepo.save(product);
    //             totalAmount+=product.getPrice();
    //             totalQuantity+=1;
    //             productList.add(product);
    //         }
            
    //     }
    //     // Round total amount to 2 decimal places using Math.round()
    //     totalAmount = Math.round(totalAmount * 100.0) / 100.0;

    //     order.setTotalAmount(totalAmount);
    //     order.setQuantity(totalQuantity);
    //     order.setProducts(productList); // Set the retrieved products

    //     // Set createdAt and updatedAt with current timestamp
    //     LocalDate currentDate = LocalDate.now();
    //     order.setCreatedAt(currentDate);
    //     order.setUpdatedAt(currentDate);

    //     // Fetch and validate user
    //     User user = userRepo.findById(orderDTO.getUserId())
    //         .orElseThrow(() -> new DataNotFoundException("User with ID " + orderDTO.getUserId() + " not found"));
    //     order.setUser(user);

    //     // Set default order status to PENDING
    //     order.setStatus(Order.OrderStatus.PENDING);

    //     return orderRepo.save(order);
    // }

    //dummy methods
   

    public Order addOrder(Order order) {
        logger.info("Method addOrder started...");
        return orderRepo.save(order);
    }
    public List<Order> getAllOrders() {
        logger.info("Method getAllOrders started...");
        return orderRepo.findAll();  
    }

    public Order getOrderById(Long orderId) {
        logger.info("Method getOrderById started...");
       return orderRepo.findById(orderId).orElse(null);
    }

    public boolean deleteOrder(Long orderId) {
        logger.info("Method deleteOrder started...");
        Order order=orderRepo.findById(orderId).orElse(null);
        if(order==null){
            return false;
        }
        List<Product>product = order.getProducts();
        for(Product prod:product){
            prod.setStock(prod.getStock()+1);
            productRepo.save(prod);
        }
        orderRepo.delete(order);
        return true;
    }

    public Order updateOrder(Long orderId, Order order) {
        logger.info("Method UpdateOrder started...");
        order.setOrderId(orderId);
        //order.setStatus(OrderStatus.SHIPPED);
        return orderRepo.save(order);
}

    public List<Order> getOrdersByUserId(Long userId) {
        logger.info("Method getOrdersByUserId started...");
        return orderRepo.findByUserId(userId);
    }
    
}


// @Service
// @RequiredArgsConstructor
// public class OrderServiceImpl implements OrderService {

//     private final OrderRepo orderRepository;
//     private final ModelMapper modelMapper;

//     @Override
//     public OrderDTO addOrder(OrderDTO orderDTO) {
//         Order order = modelMapper.map(orderDTO, Order.class);
//         return modelMapper.map(orderRepository.save(order), OrderDTO.class);
//     }

//     @Override
//     public OrderDTO getOrderById(Long id) {
//         Order order = orderRepository.findById(id)
//             .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
//         return modelMapper.map(order, OrderDTO.class);
//     }

//     @Override
//     public List<OrderDTO> getAllOrders() {
//         return orderRepository.findAll()
//             .stream()
//             .map(order -> modelMapper.map(order, OrderDTO.class))
//             .collect(Collectors.toList());
//     }

//     @Override
//     public List<OrderDTO> getOrdersByUser(Long userId) {
//         return orderRepository.findByUserId(userId)
//             .stream()
//             .map(order -> modelMapper.map(order, OrderDTO.class))
//             .collect(Collectors.toList());
//     }

//     @Override
//     public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
//         Order order = orderRepository.findById(id)
//             .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

//         order.setProductId(orderDTO.getProductId());
//         order.setQuantity(orderDTO.getQuantity());
//         order.setTotalAmount(orderDTO.getTotalAmount());
//         order.setOrderDate(orderDTO.getOrderDate());
//         order.setStatus(orderDTO.getStatus());

//         return modelMapper.map(orderRepository.save(order), OrderDTO.class);
//     }

//     @Override
//     public void deleteOrder(Long id) {
//         Order order = orderRepository.findById(id)
//             .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
//         orderRepository.delete(order);
//     }
// }