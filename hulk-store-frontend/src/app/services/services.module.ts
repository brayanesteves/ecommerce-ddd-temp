import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { CategoryService } from './category.service';
import { FranchiseService } from './franchise.service';
import { ProductService } from './product.service';
import { InventoryService } from './inventory.service';
import { MovementService } from './movement.service';
import { LoginService } from './login.service';

import { HttpService } from './implementation/http.service';
import { CategoryRestApiService } from './implementation/category.service.impl';
import { FranchiseRestApiService } from './implementation/franchise.service.impl';
import { ProductRestApiService } from './implementation/product.service.iml';
import { InventoryRestApiService } from './implementation/inventory.service.impl';
import { MovementRestApiService } from './implementation/movement.service.impl';
import { LoginRestApiService } from './implementation/login.service.impl';

@NgModule({
    imports:        [ 
        BrowserModule, 
        HttpClientModule 
    ],
    providers:      [
        HttpService,
        {provide: CategoryService, useClass: CategoryRestApiService},
        {provide: FranchiseService, useClass: FranchiseRestApiService},
        {provide: ProductService, useClass: ProductRestApiService},
        {provide: InventoryService, useClass: InventoryRestApiService},
        {provide: MovementService, useClass: MovementRestApiService},
        {provide: LoginService, useClass: LoginRestApiService}
    ],
    declarations:   [],
    exports:        [],
    bootstrap:      []
})

export class ServicesModule {

}