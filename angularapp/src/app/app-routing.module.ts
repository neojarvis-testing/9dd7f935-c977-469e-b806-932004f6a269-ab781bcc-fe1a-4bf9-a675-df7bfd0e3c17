import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AdminaddproductComponent } from './components/adminaddproduct/adminaddproduct.component';
import { UserviewproductComponent } from './components/userviewproduct/userviewproduct.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegistrationComponent},
  {path:'logout',component:LogoutComponent},
  {path:'addproduct',component:AdminaddproductComponent},
  {path:'viewproduct',component:UserviewproductComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
