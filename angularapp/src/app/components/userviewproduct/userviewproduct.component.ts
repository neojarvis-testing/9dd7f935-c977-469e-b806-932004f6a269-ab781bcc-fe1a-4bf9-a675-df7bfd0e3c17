import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/models/product.model';

import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-userviewproduct',
  templateUrl: './userviewproduct.component.html',
  styleUrls: ['./userviewproduct.component.css']
})
export class UserviewproductComponent implements OnInit {
  products: any[] = [];
  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getAllProducts();
  }
  backgroundUrl:string;
  getAllProducts(): void {
    this.productService.getProducts().subscribe((data) => {
      this.products = data;
    });
  }
}
