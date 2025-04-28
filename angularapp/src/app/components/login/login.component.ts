import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginDto } from 'src/app/models/loginDTO.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  submitted: boolean = false;

  // Use a single, static background image URL (customize as needed)
  backgroundUrl: string = 'https://img.freepik.com/free-vector/ecommerce-web-store-hand-drawn-illustration_107791-10966.jpg?t=st=1745824723~exp=1745828323~hmac=52082271ee07d43fbefa9c91f1c5d142c9317583b3b72227bece9c63f8d5b5f6&w=2000';

  constructor(private formBuilder: FormBuilder,private router:Router,private service:AuthService) {}

  ngOnInit(): void {
    // Initialize reactive form:
    // - username: required and minimum 3 characters.
    // - password: required and must match the specified pattern.
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['',[Validators.required,Validators.pattern('^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$')]]
    });
  }
  

  // Convenient getter for accessing form controls in the template
  get f() {
    return this.loginForm.controls;
  }

  // Called when the form is submitted
  onSubmit(): void {
    this.submitted = true;
    //Check if form is valid
    if (this.loginForm.valid) {
      this.service.loginUser(this.loginForm.value).subscribe((data)=>{
         let user:LoginDto=data
         alert("Login Successfull!!!")
         localStorage.setItem("userId",user.userId+"")
         localStorage.setItem("userRole",user.userRole)
         localStorage.setItem("token",user.token)
         localStorage.setItem("username",user.username)
         this.router.navigate(['/'])
      },(error)=>{
        alert("Login Failed!!!")
        console.log("Error: "+JSON.stringify(error))
      })
    }
    else
     alert("Invalid Form Input")
}


}
