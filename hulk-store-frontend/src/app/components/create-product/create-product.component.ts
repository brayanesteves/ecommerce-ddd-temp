import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Product } from 'src/app/model/product.model';
import { RoutingService } from 'src/app/routing/routing.service';
import { CategoryService } from 'src/app/services/category.service';
import { FranchiseService } from 'src/app/services/franchise.service';
import { ProductService } from 'src/app/services/product.service';
import { CreateProductRequest } from 'src/app/services/request/create-product.request';
import { UpdateProductRequest } from 'src/app/services/request/update-product.request';
import { CreateProductResponse } from 'src/app/services/response/create-product.response';
import { UpdateProductResponse } from 'src/app/services/response/update-product.response';

import { Category } from '../../model/category.model';
import { Franchise } from '../../model/franchise.model';

@Component({
    selector: 'create-product',
    templateUrl: './create-product.component.html'
})

export class CreateProductComponent implements OnInit {

    formSubmitted: boolean = false;
    loading: boolean = false;

    categories: Category[] = [];
    franchises: Franchise[] = [];
    selectedProduct: Product;

    createProductForm = new FormGroup({
        code: new FormControl(this.generateRandomCode(), [Validators.required]),
        name: new FormControl('', [Validators.required]),
        category: new FormControl('', [Validators.required]),
        franchise: new FormControl('', [Validators.required]),
        purchasePrice: new FormControl(0, [Validators.required, Validators.min(1)]),
        sellingPrice: new FormControl(0, [Validators.required, Validators.min(1)])
    });

    constructor(
        private categoryService: CategoryService,
        private franchiseService: FranchiseService,
        private productService: ProductService,
        private routingService: RoutingService
    ) {
        const data: any = this.routingService.getNavigationData();
        if(data) {
            this.selectedProduct = (data as Product);
        }
    }

    ngOnInit(): void {
        this.findAllCategories();
        this.findAllFranchises();
        this.initFormDataIfProductIsSelected();
    }

    isEmpty(fieldName: string): boolean {
        return this.createProductForm.get(fieldName).hasError('required') 
                && (this.createProductForm.get(fieldName).touched || this.formSubmitted)
    }

    isNotGreaterThanZero(fieldName: string): boolean {
        return this.createProductForm.get(fieldName).hasError('min') 
                && (this.createProductForm.get(fieldName).touched || this.formSubmitted)
    }

    saveProduct(): void {

        this.formSubmitted = true;
        this.loading = true;

        if(this.createProductForm.valid) {
            const request: CreateProductRequest = this.convertFormDataToRequest();
            this.productService.saveProduct(request).subscribe(
                (response: CreateProductResponse) => {

                    this.goToProductList();
                    
                }
            ).add( () => this.loading = false )
        } else {
            this.loading = false;
        }

    }

    updateProduct(): void {
        
        this.formSubmitted = true;
        this.loading = true;

        if(this.createProductForm.valid) {
            const request: UpdateProductRequest = this.convertFormDataToRequest();
            const productId: string = this.selectedProduct.id;
            this.productService.updateProduct(productId, request).subscribe(
                (response: UpdateProductResponse) => {

                    this.goToProductList();

                }
            ).add( () => this.loading = false)
        } else {
            this.loading = false;
        }

    }

    isEditingAnExistingProduct(): boolean {
        return ( this.selectedProduct !== null && this.selectedProduct !== undefined );
    }

    goToProductList(): void {
        this.routingService.goToProductList()
    }

    private generateRandomCode(): string {
        return Math.random().toString(36).substring(7).toUpperCase();
    }

    private findAllCategories(): void {
        this.loading = true;
        this.categoryService.findAllCategories().subscribe(
            (categories: Category[]) => { 
                this.categories = categories;
            }
        ).add( () => this.loading = false );
    }

    private findAllFranchises(): void {
        this.franchiseService.findAllFranchises().subscribe(
            (franchises: Franchise[]) => { 
                this.franchises = franchises;
            }
        ).add( () => this.loading = false );
    }

    private convertFormDataToRequest(): CreateProductRequest {
        let request: CreateProductRequest = new CreateProductRequest();
        request.code = this.createProductForm.get('code').value;
        request.name = this.createProductForm.get('name').value;
        request.purchasePrice = this.createProductForm.get('purchasePrice').value;
        request.sellingPrice = this.createProductForm.get('sellingPrice').value;
        request.categoryId = this.createProductForm.get('category').value;
        request.franchiseId = this.createProductForm.get('franchise').value;
        return request;
    }

    private initFormDataIfProductIsSelected(): void {

        if(this.selectedProduct) {
            this.createProductForm.controls.category.setValue( this.selectedProduct.category.id );
            this.createProductForm.controls.franchise.setValue( this.selectedProduct.franchise.id );
            this.createProductForm.controls.code.setValue( this.selectedProduct.code );
            this.createProductForm.controls.name.setValue( this.selectedProduct.name );
            this.createProductForm.controls.purchasePrice.setValue( this.selectedProduct.purchasePrice );
            this.createProductForm.controls.sellingPrice.setValue( this.selectedProduct.sellingPrice );
        }

    }

}