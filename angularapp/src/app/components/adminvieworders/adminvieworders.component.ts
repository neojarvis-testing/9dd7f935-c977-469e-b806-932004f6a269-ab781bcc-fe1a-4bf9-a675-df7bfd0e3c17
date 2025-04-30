import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/models/order.model';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-adminvieworders',
  templateUrl: './adminvieworders.component.html',
  styleUrls: ['./adminvieworders.component.css']
})
export class AdminviewordersComponent implements OnInit {
 orders: Order[] = [];
  selectedOrder: Order;
  userId: number;
  showPopup: boolean = false;
  popupTitle: string = '';
  popupMessage: string = '';
  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.getAllOrders();
  }

  // Get orders by user ID when admin searches
  getOrdersByUserId(): void {
    if (this.userId) {
      this.orderService.getOrderByUserId(this.userId).subscribe(data => {
        this.orders = data;
        console.log(`Orders for user ${this.userId}:`, this.orders);
      });
    } else {
      this.getAllOrders(); // If no user ID is entered, show all orders
    }
  }

  // Get all orders (Admin can see all orders)
  getAllOrders(): void {
    this.orderService.getOrders().subscribe(data => {
      this.orders = data;
      console.log('All Orders:', JSON.stringify(data));
    });
  }

  // View order details
  getOrderDetails(orderId: number): void {
   
    this.orderService.getOrderDetails(orderId).subscribe(data => {
      this.selectedOrder = data;
      console.log("works")
      console.log('Full Order Details:', JSON.stringify(this.selectedOrder, null, 2));
    });
  }
  updateOrderStatus(orderId: number, status: string) {
    this.orderService.updateOrderStatus(orderId, status).subscribe((updatedOrder) => {
      // Update the status in the frontend with the value from backend
      const orderIndex = this.orders.findIndex(order => order.orderId === orderId);
      if (orderIndex !== -1) {
        this.orders[orderIndex].status = updatedOrder.status; // Update the displayed order status
      }
    });
  }
  

}


