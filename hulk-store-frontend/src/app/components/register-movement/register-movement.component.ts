import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2'
import { Observable } from 'rxjs';

import { InventoryItem } from 'src/app/model/inventory-item.model';
import { Product } from 'src/app/model/product.model';
import { RoutingService } from 'src/app/routing/routing.service';
import { MovementService } from 'src/app/services/movement.service';
import { RegisterMovementRequest } from 'src/app/services/request/register-movement.request';
import { RegisterMovementResponse } from 'src/app/services/response/register-movement.response';

@Component({
    selector: 'register-movement',
    templateUrl: './register-movement.component.html'
})

export class RegisterMovementComponent implements OnInit {

    loading: boolean = false;
    formSubmitted: boolean = false; 
    itemSelected: InventoryItem;
    product: Product;

    registerMovementForm = new FormGroup({
        movementType: new FormControl('INCOMINGS', [Validators.required]),
        code: new FormControl('', [Validators.required]),
        name: new FormControl('', [Validators.required]),
        quantity: new FormControl(0, [Validators.required, Validators.min(1)]),
        unitPrice: new FormControl(0, [Validators.required, Validators.min(0)]),
        subTotal: new FormControl(0, [Validators.required, Validators.min(0)]), 
        observation: new FormControl('', []),
    });

    constructor(
        private routingService: RoutingService,
        private movementService: MovementService
    ) {
        this.itemSelected = this.routingService.getNavigationData();
    }

    ngOnInit() {

        if(!this.itemSelected) {
            this.routingService.goToProductList();
        }

        if(this.itemSelected) {
            const product: Product = this.itemSelected.product;
            this.registerMovementForm.controls.code.setValue( product.code );
            this.registerMovementForm.controls.name.setValue( product.name );
            this.registerMovementForm.controls.unitPrice.setValue( product.purchasePrice );
            this.registerMovementForm.controls.quantity.setValue( 0 );
            this.onChangeQuantityOrUnitPrice();
        }
    }

    save(): void {

        this.formSubmitted = true;

        if( this.registerMovementForm.valid ) {

            if( this.quantityIsValid() ) {
                this.registerMovement();
            } else {
                const currentStock: number = this.itemSelected.inStock();

                if(currentStock === 0) {

                    Swal.fire(
                        'Error',
                        'No se pueden hacer movimientos de salida, ya que el producto no tiene unidades disponibles en stock',
                        'error'
                    );

                } else {

                    Swal.fire(
                        'Error',
                        `Este producto solamente tiene disponibles ${currentStock} ${ currentStock > 1 ? 'unidades' : 'unidad'} en stock`,
                        'error'
                    );

                }
            }

        }

    }

    onchangeMovementType(): void {

        if( this.isIncomingMovement() ) {
            this.registerMovementForm.controls.unitPrice.setValue( this.itemSelected?.product.purchasePrice );
        }

        if( this.isOutgoingMovement() ) {
            this.registerMovementForm.controls.unitPrice.setValue( this.itemSelected?.product.sellingPrice );
        }

    }

    onChangeQuantityOrUnitPrice(): void {

        const unitPrice: number = this.registerMovementForm.get('unitPrice').value;
        const quantity: number = this.registerMovementForm.get('quantity').value;

        if(unitPrice && quantity) {

            const subTotal: number = unitPrice * quantity;

            this.registerMovementForm.controls.subTotal.setValue( subTotal );

        } else {
            this.registerMovementForm.controls.subTotal.setValue( 0 );
        }

    }

    cancel(): void {
        this.routingService.goToProductList();
    }

    isEmpty(fieldName: string): boolean {
        return this.registerMovementForm.get(fieldName).hasError('required') 
                && (this.registerMovementForm.get(fieldName).touched || this.formSubmitted)
    }

    isNegative(fieldName: string): boolean {
        return this.registerMovementForm.get(fieldName).hasError('min') 
                && (this.registerMovementForm.get(fieldName).touched || this.formSubmitted)
    }

    private quantityIsValid(): boolean {
        let isValid: boolean = true;
        if( this.isOutgoingMovement() ) {
            const currentStock: number = this.itemSelected.inStock();
            const quantity: number = this.registerMovementForm.get('quantity').value;
            isValid = ( quantity <= currentStock );
        }
        return isValid;
    }

    private isIncomingMovement(): boolean {
        const type = this.registerMovementForm.get('movementType').value;
        return type === 'INCOMINGS';
    }

    private isOutgoingMovement(): boolean {
        const type = this.registerMovementForm.get('movementType').value;
        return type === 'OUTGOINGS';
    }

    private registerMovement(): void {
        const request: RegisterMovementRequest = this.getRequest();
        const productId = this.itemSelected.product.id;

        let registerMovement$: Observable<RegisterMovementResponse> = null;

        if( this.isIncomingMovement() ) {
            registerMovement$ = this.movementService.registerIncomingMovement( productId, request );
        } else if( this.isOutgoingMovement() ) {
            registerMovement$ = this.movementService.registerOutgoingMovement( productId, request );
        }

        this.loading = true;

        registerMovement$.subscribe(
            (response: RegisterMovementResponse) => {
                this.routingService.goToMovementList();
            }
        ).add( () => this.loading = false );
    }

    private getRequest(): RegisterMovementRequest {
        let request: RegisterMovementRequest = new RegisterMovementRequest();
        request.quantity = this.registerMovementForm.get('quantity').value;
        request.unitPrice = this.registerMovementForm.get('unitPrice').value;
        request.observation = this.registerMovementForm.get('observation').value;
        return request;
    }

}