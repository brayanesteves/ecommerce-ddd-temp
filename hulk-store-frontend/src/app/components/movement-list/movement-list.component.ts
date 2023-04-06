import { Component, OnInit } from '@angular/core';
import { MovementListItem } from 'src/app/model/movement-list-item.model';
import { Product } from 'src/app/model/product.model';
import { MovementService } from 'src/app/services/movement.service';
import { ProductService } from 'src/app/services/product.service';

import { RoutingService } from '../../routing/routing.service';

@Component({
    selector: 'movement-list',
    templateUrl: './movement-list.component.html'
})

export class MovementListComponent implements OnInit {

    loading: boolean = false;
    movementListItems: MovementListItem[] = [];
    allProducts: Product[] = [];
    selectedProductId: string;

    constructor(
        private movementServive: MovementService,
        private productService: ProductService,
        private routingService: RoutingService
    ) {}

    ngOnInit() {
        this.findAllMovements();
        this.findAllProducts();
    }

    onChangeProduct(): void {

        if(this.selectedProductId) {
            this.findAllMovementsBySelectedProduct();
        } else {
            this.findAllMovements();
        }

    }

    goToHomePage(): void {
        this.routingService.goToProductList();
    }

    private findAllMovements(): void {

        this.loading = true;

        this.movementServive.getAllMovements().subscribe(
            (response: MovementListItem[]) => {
                this.movementListItems = response;
            }
        ).add( () => this.loading = false );

    }

    private findAllMovementsBySelectedProduct(): void {

        this.loading = true;

        this.movementServive.getAllMovementsByProduct( this.selectedProductId ).subscribe(
            (response: MovementListItem[]) => {
                this.movementListItems = response;
            }
        ).add( () => this.loading = false );

    }

    private findAllProducts(): void {

        this.loading = true;

        this.productService.findAllProducts().subscribe(
            (response: Product[]) => this.allProducts = response
        ).add( () => this.loading = false )

    }

}