import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  cart: any[];
  displayPopup: boolean = false; // controls the popup visibility

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router,
    private readonly cartService: CartService
  ) { }

  ngOnInit(): void {
    // Save cart items to localStorage before logout
    this.cart = this.cartService.getCartItems();
    localStorage.setItem("cart", JSON.stringify(this.cart));

    // Display the custom confirmation popup
    this.displayPopup = true;
  }

  onConfirm(): void {
    // Logout the user and navigate home when confirmed
    this.authService.loggedOut();
    this.displayPopup = false;
    this.router.navigate(['/']);
  }

  onCancel(): void {
    // Simply hide popup and navigate home if cancelled
    this.displayPopup = false;
    this.router.navigate(['/']);
  }
}
