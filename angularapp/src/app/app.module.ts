import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminaddproductComponent } from './components/adminaddproduct/adminaddproduct.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule , HTTP_INTERCEPTORS} from '@angular/common/http';
import { AdminnavbarComponent } from './components/adminnavbar/adminnavbar.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
import { AdminviewordersComponent } from './components/adminvieworders/adminvieworders.component';
import { AdminviewproductComponent } from './components/adminviewproduct/adminviewproduct.component';
import { AdminviewuserdetailsComponent } from './components/adminviewuserdetails/adminviewuserdetails.component';
import { AuthguardComponent } from './components/authguard/authguard.component';
import { ErrorComponent } from './components/error/error.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { UseraddcartComponent } from './components/useraddcart/useraddcart.component';
import { UseraddfeedbackComponent } from './components/useraddfeedback/useraddfeedback.component';
import { UsernavbarComponent } from './components/usernavbar/usernavbar.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';
import { UserviewordersComponent } from './components/uservieworders/uservieworders.component';
import { UserviewproductComponent } from './components/userviewproduct/userviewproduct.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AuthInterceptor } from './auth.interceptor';
import { UserviewcartComponent } from './userviewcart/userviewcart.component';
import { ParticlesBackgroundComponent } from './components/particles-background/particles-background.component';
import { NgxPaginationModule } from 'ngx-pagination';


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
    UserviewproductComponent,
    LogoutComponent,
    UserviewcartComponent,
    ParticlesBackgroundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    HttpClientModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
