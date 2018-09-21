
import { HomeComponent } from './home/home.component';
import { Routes } from '@angular/router';
import { RecentTacosComponent } from './recents/recents.component';
import { SpecialsComponent } from './specials/specials.component';
import { DesignComponent } from './design/design.component';
import { LocationsComponent } from './locations/locations.component';
import { CartComponent } from './cart/cart.component';
import { LoginComponent } from './login/login.component';

export const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'recents',
    component: RecentTacosComponent
  },
  {
    path: 'specials',
    component: SpecialsComponent
  },
  {
    path: 'locations',
    component: LocationsComponent
  },
  {
    path: 'design',
    component: DesignComponent
  },
  {
    path: 'cart',
    component: CartComponent
  },
  {
    path: '**',
    redirectTo: 'home',
    pathMatch: 'full'
  },
];
