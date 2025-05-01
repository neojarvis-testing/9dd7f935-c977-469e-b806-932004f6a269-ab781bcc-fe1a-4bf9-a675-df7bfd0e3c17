import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AdminaddproductComponent } from './components/adminaddproduct/adminaddproduct.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
import { UseraddfeedbackComponent } from './components/useraddfeedback/useraddfeedback.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';
import { AdminviewproductComponent } from './components/adminviewproduct/adminviewproduct.component';
import { UserviewordersComponent } from './components/uservieworders/uservieworders.component';
import { UseraddcartComponent } from './components/useraddcart/useraddcart.component';
import { AdminviewordersComponent } from './components/adminvieworders/adminvieworders.component';
import { UserviewcartComponent } from './userviewcart/userviewcart.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegistrationComponent},
  {path:'logout',component:LogoutComponent},
  {path:'addproduct',component:AdminaddproductComponent},
  {path:'addproduct/:id',component:AdminaddproductComponent},
  {path:'viewproduct',component:AdminviewproductComponent},
  {path:'add-feedback',component:UseraddfeedbackComponent},
  {path:'my-feedbacks',component:UserviewfeedbackComponent},
  {path:'view-feedbacks',component:AdminviewfeedbackComponent},
  {path:'viewuserorders',component:UserviewordersComponent},
  {path:'gotocart',component:UseraddcartComponent},
  {path:'adminvieworders',component:AdminviewordersComponent},
  {path:'viewusercart',component:UserviewcartComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
