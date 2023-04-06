import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/model/category.model';
import { Franchise } from 'src/app/model/franchise.model';
import { Product } from 'src/app/model/product.model';

import { RoutingService } from 'src/app/routing/routing.service';
import { CategoryService } from 'src/app/services/category.service';
import { FranchiseService } from 'src/app/services/franchise.service';
import { FilterOptions } from 'src/app/services/request/filter-options.request';
import { InventoryItem } from '../../model/inventory-item.model';
import { InventoryService } from '../../services/inventory.service';

@Component({
    selector: 'product-list',
    templateUrl: './product-list.component.html'
})

export class ProductListComponent implements OnInit {

    inventoryItems: InventoryItem[] = [];
    franchises: Franchise[] = [];
    categories: Category[] = [];
    selectedFranchiseId: string;
    selectedCategoryId: string;

    loading: boolean = false;

    constructor(
        private routingService: RoutingService,
        private inventoryService: InventoryService,
        private franchiseService: FranchiseService,
        private categoryService: CategoryService
    ) {}

    ngOnInit(): void {
        this.findAllInventoryItems();
        this.findAllFranchises();
        this.findAllCategories();
    }

    goToCreateProduct(): void {
        this.routingService.goToCreateProduct();
    }

    goToRegisterMovement(itemSelected: InventoryItem): void {
        this.routingService.goToRegisterMovement(itemSelected);
    }

    goToMovementList(): void {
        this.routingService.goToMovementList();
    }

    goToEditProduct(product: Product): void {
        this.routingService.goToEditProduct(product);
    }

    onChangeSelectedFranchise(): void {

        this.findAllInventoryItems();

    }

    onChangeSelectecCategory(): void {

        this.findAllInventoryItems();

    }

    private findAllInventoryItems(): void {

        this.loading = true;

        let filter: FilterOptions = null;
        
        if(this.selectedCategoryId || this.selectedFranchiseId) {
            filter = new FilterOptions();
            if(this.selectedFranchiseId) {
                filter.franchiseId = this.selectedFranchiseId;
            }
            if(this.selectedCategoryId) {
                filter.categoryId = this.selectedCategoryId;
            }
        }

        this.inventoryService.findAllInventoryItems(filter).subscribe(
            (response: InventoryItem[]) => {
                this.inventoryItems = response;
            }
        ).add( () => this.loading = false );

    }

    private findAllFranchises(): void {

        this.loading = true;

        this.franchiseService.findAllFranchises().subscribe(
            (response: Franchise[]) => this.franchises = response
        ).add( () => this.loading = false );

    }

    private findAllCategories(): void {

        this.loading = true;

        this.categoryService.findAllCategories().subscribe(
            (response: Category[]) => this.categories = response
        ).add( () => this.loading = false );

    }

}