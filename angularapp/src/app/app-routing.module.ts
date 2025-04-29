import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AdminaddproductComponent } from './components/adminaddproduct/adminaddproduct.component';
import { UserviewproductComponent } from './components/userviewproduct/userviewproduct.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
import { UseraddfeedbackComponent } from './components/useraddfeedback/useraddfeedback.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegistrationComponent},
  {path:'logout',component:LogoutComponent},
  {path:'addproduct',component:AdminaddproductComponent},
  {path:'viewproduct',component:UserviewproductComponent},
  {path:'add-feedback',component:UseraddfeedbackComponent},
  {path:'my-feedbacks',component:UserviewfeedbackComponent},
  {path:'view-feedbacks',component:AdminviewfeedbackComponent} 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
