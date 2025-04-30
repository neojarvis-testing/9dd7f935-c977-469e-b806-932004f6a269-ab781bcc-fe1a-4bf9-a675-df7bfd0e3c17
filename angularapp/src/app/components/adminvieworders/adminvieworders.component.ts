import { Component, OnInit, OnDestroy } from '@angular/core';
import { Order } from 'src/app/models/order.model';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-adminvieworders',
  templateUrl: './adminvieworders.component.html',
  styleUrls: ['./adminvieworders.component.css']
})
export class AdminviewordersComponent implements OnInit, OnDestroy {
  orders: Order[] = [];
  selectedOrder: Order;
  userId: number;
  
  // Confirmation popup properties
  showConfirmationPopup: boolean = false;
  confirmationPopupTitle: string = '';
  confirmationPopupMessage: string = '';
  selectedOrderIdForUpdate: number;
  newStatusForUpdate: string;
  
  // Error popup properties (for update errors)
  showErrorPopup: boolean = false;
  errorPopupTitle: string = '';
  errorPopupMessage: string = '';

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    // Set background image dynamically via TypeScript
    document.body.style.background = "url('https://img.freepik.com/free-vector/flat-world-post-day-background_23-2149084335.jpg?t=st=1746012660~exp=1746016260~hmac=169799412ad65a722e5b45f37395833bc1543af5991a100f93556ecc81b26109&w=1380') no-repeat center center fixed";
    document.body.style.backgroundSize = "cover";

    this.getAllOrders();
  }

  ngOnDestroy(): void {
    // Optionally reset the background when the component is destroyed
    document.body.style.background = '';
  }

  // Get orders by user ID (if provided); otherwise fetch all orders
  getOrdersByUserId(): void {
    if (this.userId) {
      this.orderService.getOrderByUserId(this.userId).subscribe(data => {
        this.orders = data;
        console.log(`Orders for user ${this.userId}:`, this.orders);
      });
    } else {
      this.getAllOrders();
    }
  }

  // Fetch all orders (Admin view)
  getAllOrders(): void {
    this.orderService.getOrders().subscribe(data => {
      this.orders = data;
      console.log('All Orders:', JSON.stringify(data));
    });
  }

  // Retrieve full details for a specific order
  getOrderDetails(orderId: number): void {
    this.orderService.getOrderDetails(orderId).subscribe(data => {
      this.selectedOrder = data;
      console.log('Full Order Details:', JSON.stringify(this.selectedOrder, null, 2));
    });
  }

  // Opens the confirmation popup for order status updates
  openConfirmationPopup(orderId: number, status: string): void {
    this.selectedOrderIdForUpdate = orderId;
    this.newStatusForUpdate = status;
    this.confirmationPopupTitle = 'Confirm Status Update';
    this.confirmationPopupMessage = `Are you sure you want to mark Order #${orderId} as ${status}?`;
    this.showConfirmationPopup = true;
  }

  closeConfirmationPopup(): void {
    this.showConfirmationPopup = false;
  }

  confirmUpdate(): void {
    this.orderService.updateOrderStatus(this.selectedOrderIdForUpdate, this.newStatusForUpdate).subscribe(
      (updatedOrder) => {
        // Update the order status locally for a faster UI response
        const orderIndex = this.orders.findIndex(order => order.orderId === this.selectedOrderIdForUpdate);
        if (orderIndex !== -1) {
          this.orders[orderIndex].status = updatedOrder.status;
        }
        this.closeConfirmationPopup();
      },
      error => {
        this.closeConfirmationPopup();
        this.showError('Update Failed', 'Failed to update order status. Please try again later.');
      }
    );
  }

  // Display an error popup with a custom message
  showError(title: string, message: string): void {
    this.errorPopupTitle = title;
    this.errorPopupMessage = message;
    this.showErrorPopup = true;
  }

  closeErrorPopup(): void {
    this.showErrorPopup = false;
  }
}
