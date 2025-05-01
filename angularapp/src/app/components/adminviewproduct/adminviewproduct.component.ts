import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-adminviewproduct',
  templateUrl: './adminviewproduct.component.html',
  styleUrls: ['./adminviewproduct.component.css']
})
export class AdminviewproductComponent implements OnInit {
  products: any[] = [];
  filteredProducts: any[] = [];
  searchTerm: string = '';
  selectedCategory: string = '';
  showPopup: boolean = false;
  popupTitle: string = '';
  popupMessage: string = '';
  confirmDelete: boolean = false;
  productIdToDelete: number | null = null;
  isCartEmpty: boolean = true;

  constructor(private productService: ProductService, private router: Router, public authService: AuthService, private cartService: CartService) { }

  ngOnInit(): void {
    this.getAllProducts();
    this.checkCartStatus();
  }

  getAllProducts(): void {
    this.productService.getProducts().subscribe((data) => {
      this.products = data;
      this.filteredProducts = data;
    });
  }

  checkCartStatus(): void {
    this.isCartEmpty = this.cartService.getCartItems().length === 0;
  }

  filterProducts(): void {
    this.filteredProducts = this.products.filter(product => 
      product.name.toLowerCase().includes(this.searchTerm.toLowerCase()) &&
      (this.selectedCategory === '' || product.category === this.selectedCategory)
    );
  }

  editProduct(productId: number): void {
    this.router.navigate(['/addproduct', productId]);
  }

  confirmDeleteProduct(productId: number): void {
    this.productIdToDelete = productId;
    this.showPopupMsg('Confirm Deletion', 'Are you sure you want to delete this product?');
    this.confirmDelete = true;
  }

  deleteProduct(): void {
    if (this.productIdToDelete !== null) {
      this.productService.deleteProduct(this.productIdToDelete).subscribe(() => {
        this.products = this.products.filter(product => product.productId !== this.productIdToDelete);
        this.filteredProducts = this.filteredProducts.filter(product => product.productId !== this.productIdToDelete);
        this.showPopupMsg('Success', 'Product deleted successfully!');
        this.getAllProducts();
      }, error => {
        this.showPopupMsg('Error', 'Failed to delete the product. Please try again.');
      });
    }
    this.confirmDelete = false;
  }

  addToCart(id: number): void {
    this.showPopupMsg('Success', 'Product added to Cart..!!');
    this.cartService.addProductToCart(id);
    this.checkCartStatus();
  }

  redirectToCart(): void {
    this.router.navigate(['/gotocart']);
  }

  showPopupMsg(title: string, message: string): void {
    this.popupTitle = title;
    this.popupMessage = message;
    this.showPopup = true;
  }

  closePopup(confirm: boolean = false): void {
    this.showPopup = false;
    if (confirm && this.confirmDelete) {
      this.deleteProduct();
    }
    this.confirmDelete = false;
  }
}
