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

  deleteCart(index: number): void {
    if (index >= 0 && index < this.product.length) {
        const removedProduct = this.product.splice(index, 1);
        console.log('Product removed:', removedProduct);
    } else {
        console.log('Invalid index:', index);
    }
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
