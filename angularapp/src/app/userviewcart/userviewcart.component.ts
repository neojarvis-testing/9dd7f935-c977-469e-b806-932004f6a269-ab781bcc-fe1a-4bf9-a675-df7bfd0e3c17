import { Component, OnInit } from '@angular/core';
import { CartService } from '../services/cart.service';
import { Product } from '../models/product.model';
import { Router } from '@angular/router';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-userviewcart',
  templateUrl: './userviewcart.component.html',
  styleUrls: ['./userviewcart.component.css']
})
export class UserviewcartComponent implements OnInit {

  products: Product[] = [];

  constructor(
    private readonly service: CartService,
    private readonly router: Router,
    private readonly productService: ProductService
  ) { }

  ngOnInit(): void {
    const productIds = this.service.getCartItems();
    productIds.forEach(id => {
      this.getProductById(id);
    });
  }

  getProductById(id: number): void {
    this.productService.getProductById(id).subscribe((data: Product) => {
      this.products.push(data);
    });
  }

  redirectToCart(): void {
    this.router.navigate(['/gotocart']);
  }

  redirectToViewProducts(): void {
    this.router.navigate(['/viewproduct']);
  }

  deleteProductFromCart(index: number): void {
    console.log('Deleting product at index:', index);
    this.service.deleteCart(index);
    this.products.splice(index, 1); // Directly update the local products array
    console.log('Updated products:', this.products);
  }

  clearCart() {
    this.service.clearCart();
    this.products = [];
  }

}
