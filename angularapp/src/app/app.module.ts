import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminaddproductComponent } from './components/adminaddproduct/adminaddproduct.component';
import { AdminnavbarComponent } from './components/adminaddproduct/adminnavbar/adminnavbar.component';
import { AdminviewfeedbackComponent } from './adminviewfeedback/adminviewfeedback.component';
import { AdminviewordersComponent } from './adminvieworders/adminvieworders.component';
import { AdminviewproductComponent } from './adminviewproduct/adminviewproduct.component';
import { AdminviewuserdetailsComponent } from './adminviewuserdetails/adminviewuserdetails.component';
import { AuthguardComponent } from './authguard/authguard.component';
import { ErrorComponent } from './error/error.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RegistrationComponent } from './registration/registration.component';
import { UseraddcartComponent } from './useraddcart/useraddcart.component';
import { UseraddfeedbackComponent } from './useraddfeedback/useraddfeedback.component';
import { UsernavbarComponent } from './usernavbar/usernavbar.component';
import { UserviewfeedbackComponent } from './userviewfeedback/userviewfeedback.component';
import { UserviewordersComponent } from './uservieworders/uservieworders.component';
import { UserviewproductComponent } from './userviewproduct/userviewproduct.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminaddproductComponent,
    AdminnavbarComponent,
    AdminviewfeedbackComponent,
    AdminviewordersComponent,
    AdminviewproductComponent,
    AdminviewuserdetailsComponent,
    AuthguardComponent,
    ErrorComponent,
    HomeComponent,
    LoginComponent,
    NavbarComponent,
    RegistrationComponent,
    UseraddcartComponent,
    UseraddfeedbackComponent,
    UsernavbarComponent,
    UserviewfeedbackComponent,
    UserviewordersComponent,
    UserviewproductComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
