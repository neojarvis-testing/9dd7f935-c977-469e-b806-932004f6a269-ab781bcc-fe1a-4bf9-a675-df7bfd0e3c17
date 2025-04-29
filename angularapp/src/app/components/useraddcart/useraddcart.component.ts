import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Order } from 'src/app/models/order.model';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-useraddcart',
  templateUrl: './useraddcart.component.html',
  styleUrls: ['./useraddcart.component.css']
})
export class UseraddcartComponent implements OnInit {
  orderForm:FormGroup
  orders: Order[] = [];
  order!: Order;
   // Popup properties
   showPopup: boolean = false;
   popupTitle: string = '';
   popupMessage: string = '';

  constructor(private orderService: OrderService,private fb:FormBuilder,private cartServive:CartService,private router:Router) { 
  }
  clearCart():void{
    this.cartServive.clearCart()
    this.orderForm.reset()
    this.showPopupMsg("Clear", "Cart cleared successfully!!!");
  }

  ngOnInit(): void {
    this.orderForm=this.fb.group({
      address: ['', [Validators.required]],
      userId: [null],
      product: [[]]
      })
    this.orderForm.value.product=this.cartServive.getCartItems()
    this.orderForm.value.userId=+ localStorage.getItem('userId')
  }

  placeOrder(): void {
    if(this.orderForm.valid){
    this.orderService.placeOrder(this.orderForm.value).subscribe(data => {
          console.log('Order placed successfully:', data);
          this.showPopupMsg("Success", "Order placed successfully!!!");
        },
        error => {
          console.error('Order placement error:', error);
          this.showPopupMsg("Error", "Order placement failed!!!");
        });
      }
     else {
          // Mark all controls as touched to trigger validation messages.
          this.orderForm.markAllAsTouched();
        }
  
}
 // Helper method to show the custom popup
 showPopupMsg(title: string, message: string): void {
  this.popupTitle = title;
  this.popupMessage = message;
  this.showPopup = true;
}

// Closes the popup and navigates upon success
closePopup(): void {
  this.showPopup = false;
  // Navigate to "/viewuserorders" only if the order was placed successfully.
  if (this.popupTitle === "Success") {
    this.router.navigate(['/viewuserorders']);
  }
  else if(this.popupTitle == "Clear") {
    this.router.navigate(['/']);
  }
}
}