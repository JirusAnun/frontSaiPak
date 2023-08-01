import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HeaderComponent } from './components/header/header.component';
import { MainComponent } from './components/main/main.component';
import { canActivateTeam } from './services/auth-guard.service';
import { ShopComponent } from './components/shop/shop.component';
import { AdminComponent } from './components/admin/admin.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'shop',
    component: ShopComponent,
    canActivate: [canActivateTeam]
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [canActivateTeam]
  },
  {
    path: '',
    component: MainComponent,
    canActivate: [canActivateTeam]
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
