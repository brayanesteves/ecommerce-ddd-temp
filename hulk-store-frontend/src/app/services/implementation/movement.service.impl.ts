import { forkJoin, Observable } from "rxjs";
import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';

import { environment } from '../../../environments/environment';
import { MovementService } from "../movement.service";
import { RegisterMovementRequest } from "../request/register-movement.request";
import { RegisterMovementResponse } from "../response/register-movement.response";
import { HttpService } from './http.service';
import { MovementListItem } from '../../model/movement-list-item.model';
import { Movement } from '../../model/movement.model';
import { ProductService } from "../product.service";
import { Product } from "src/app/model/product.model";

@Injectable({
    providedIn: 'root'
})

export class MovementRestApiService extends MovementService {

    constructor(
        private httpService: HttpService,
        private productService: ProductService  
    ) {
        super();
    }

    registerIncomingMovement(productId: string, request: RegisterMovementRequest): Observable<RegisterMovementResponse> {

        return this.registerMovement(productId, request, 'incomings');

    }

    registerOutgoingMovement(productId: string, request: RegisterMovementRequest): Observable<RegisterMovementResponse> {

        return this.registerMovement(productId, request, 'outgoings');

    }

    getAllMovements(): Observable<MovementListItem[]> {

        const url: string = `${environment.api.host}/api/movements`;

        const allMovements$: Observable<Movement[]> = this.httpService.get(url, true).pipe(
            map( response => response.movements as Movement[] )
        );

        const allProducts$: Observable<Product[]> = this.productService.findAllProducts();

        return forkJoin([ allMovements$, allProducts$ ]).pipe(
            map(([ allMovements, allProducts ]) => {

                return allMovements.map( (movement: Movement) => {

                    const product: Product = allProducts.find(p => p.id === movement.productId);

                    let item: MovementListItem  = new MovementListItem();
                    item.movement = movement;
                    item.product = product;
                    return item;

                })

            })
        );

    }

    getAllMovementsByProduct(productId: string): Observable<MovementListItem[]> {

        const url: string = `${environment.api.host}/api/movements?productId=${productId}`;

        const allMovementsByProduct$: Observable<Movement[]> = this.httpService.get(url, true).pipe(
            map( response => response.movements as Movement[] )
        );

        const product$: Observable<Product> = this.productService.findById(productId);

        return forkJoin([ allMovementsByProduct$, product$ ]).pipe(
            map(([ allMovementsByProduct, product ]) => {

                return allMovementsByProduct.map( (movement: Movement) => {

                    let item: MovementListItem  = new MovementListItem();
                    item.movement = movement;
                    item.product = product;
                    return item;

                })

            })
        );

    }

    private registerMovement(productId: string, request: RegisterMovementRequest, movementType: string): Observable<RegisterMovementResponse> {

        const url: string = `${environment.api.host}/api/movements/${productId}/${movementType}`;

        return this.httpService.post(url, request, true).pipe(
            map( response => response as RegisterMovementResponse )
        );

    }

}