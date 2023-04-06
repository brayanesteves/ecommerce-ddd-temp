import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RoutingService } from './routing.service';

import { AuthenticationGuard } from './authentication.guard';

import { CreateProductComponent } from '../components/create-product/create-product.component';
import { ProductListComponent } from '../components/product-list/product-list.component';
import { RegisterMovementComponent } from '../components/register-movement/register-movement.component';
import { MovementListComponent } from '../components/movement-list/movement-list.component';
import { LoginComponent } from '../components/login/login.component';
import { CreateUserComponent } from '../components/create-user/create-user.component';

const routes: Routes = [
    {path: 'create-product', component: CreateProductComponent, canActivate: [AuthenticationGuard]},
    {path: 'products', component: ProductListComponent, canActivate: [AuthenticationGuard]},
    {path: 'register-movement', component: RegisterMovementComponent, canActivate: [AuthenticationGuard]},
    {path: 'movement-list', component: MovementListComponent, canActivate: [AuthenticationGuard]},
    {path: 'login', component: LoginComponent},
    {path: 'sign-up', component: CreateUserComponent},
    {path: '**', redirectTo: '/login', pathMatch: 'full'}
];

@NgModule({
    imports:        [ CommonModule, RouterModule.forRoot(routes) ],
    providers:      [ RoutingService ],
    declarations:   [],
    exports:        [],
    bootstrap:      []
})

export class RoutingModule {
}