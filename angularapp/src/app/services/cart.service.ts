import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  product:any[]=[]
 constructor() { }
addProductToCart(product: any): void {
    this.product.push(product);
    console.log('Product added:', product);
  }
deleteCart(id: number): void {
    this.product = this.product.filter(item => item.id !== id);
    console.log('Product removed:', id);
  }
 addMultipleProductsToCart(products: any[]): void {
    this.product = [...this.product, ...products]; // Merging arrays
    console.log('Products added:', products);
}

 clearCart(): void {
    this.product = [];
    console.log('Cart cleared');
  }

  getCartItems(): any[] {
    return this.product;
  }
}
