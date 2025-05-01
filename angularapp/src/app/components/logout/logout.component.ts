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

  constructor(private readonly service:AuthService,private readonly route:Router, private readonly cartService:CartService) { }
  cart:any[]
  ngOnInit(): void {
    this.cart=this.cartService.getCartItems()
    localStorage.setItem("cart",JSON.stringify(this.cart))
    if(confirm("Do you want to logout??"))
        this.service.loggedOut();
    this.route.navigate(["/"])
  }

}
