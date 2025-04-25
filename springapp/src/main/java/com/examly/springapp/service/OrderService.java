package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.dto.OrderDTO;
import com.examly.springapp.model.Order;





public interface OrderService {
//      public Order addOrder(OrderDTO orderDTO);
//      public List<Order> getAllOrders();
//      public Order getOrderById(Long orderId);
//      public boolean deleteOrder(Long orderId);
//      public List<Order>getOrdersByUser(Long userId);
//      //public List<Order>getOrdersByStatus(Order.OrderStatus status);
// }
 OrderDTO addOrder(OrderDTO orderDTO);

    OrderDTO getOrderById(Long id);

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOrdersByUser(Long userId);

    OrderDTO updateOrder(Long id, OrderDTO orderDTO);

    void deleteOrder(Long id);
}


