import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
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
  showPopup: boolean = false
   popupTitle: string = '';
   popupMessage: string = '';
  constructor(private productService: ProductService, private router : Router, public authService : AuthService,private cartService:CartService) { }

  ngOnInit(): void {
    this.getAllProducts();
  }
  backgroundUrl:string;
  getAllProducts(): void {
    this.productService.getProducts().subscribe((data) => {
      this.products = data;
    });
  }
  editProduct(productId: number): void {
    this.router.navigate(['/addproduct',productId]);
  }
  deleteProduct(productId: number): void {
    this.productService.deleteProduct(productId).subscribe(() => {
      this.products = this.products.filter(product => product.productId !== productId);
      // Optionally fetch the updated product list from the server
      this.getAllProducts();
    });
  }
  addToCart(id:number){
    this.showPopupMsg("Success",'Product added to Cart..!!');
      this.cartService.addProductToCart(id)
  }
  redirectToCart() {
    this.router.navigate(['/gotocart']);
  }
  // Helper method to show the custom popup
  showPopupMsg(title: string, message: string): void {
    this.popupTitle = title;
    this.popupMessage = message;
    this.showPopup = true;
  }

  // Call this method when the user closes the popup
  closePopup(): void {
    this.showPopup = false;
    // If login was successful, navigate to the home page
    if (this.popupTitle === "Success") {
      this.router.navigate(['/viewproduct']);
    }
  }

}


