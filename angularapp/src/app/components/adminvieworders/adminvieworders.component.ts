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
  order!: Order;

  constructor(private orderService:OrderService) { }

  ngOnInit(): void {
    this.getOrders()
  }
  updateOrderStatus(id: number, updatedOrder: Order): void {
    this.orderService.updateOrderStatus(id, updatedOrder).subscribe(data => {
      console.log('Order updated:', data);
      this.getOrders(); 
    });
  }
  getOrders(): void {
    this.orderService.getOrders().subscribe(data => {
      this.orders = data;
      console.log('All orders:', data);
    });
  }
}
