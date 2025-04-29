import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/models/order.model';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-uservieworders',
  templateUrl: './uservieworders.component.html',
  styleUrls: ['./uservieworders.component.css']
})
export class UserviewordersComponent implements OnInit {
  orders: Order[] = [];
  order!: Order;
  constructor(private orderService:OrderService) { }

  ngOnInit(): void {
    this.getOrders()
  }
  deleteOrder(id: number): void {
    this.orderService.deleteOrder(id).subscribe(() => {
      console.log('Order deleted');
      this.getOrders();
    });
  }

  getOrderDetails(orderId: number): void {
    this.orderService.getOrderDetails(orderId).subscribe(data => {
      this.order = data;
      console.log('Order details:', data);
    });
  }

  getOrderByUserId(userId: number): void {
    this.orderService.getOrderByUserId(userId).subscribe(data => {
      console.log('Orders for user:', data);
    });
  }

  getOrders(): void {
    this.orderService.getOrders().subscribe(data => {
      this.orders = data;
      console.log('All orders:', data);
    });
  } 
 }
