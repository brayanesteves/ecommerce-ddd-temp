import { Injectable } from '@angular/core';
import { empty, Observable, of } from "rxjs";
import { map } from 'rxjs/operators';

import { environment } from '../../../environments/environment';
import { HttpService } from './http.service';
import { ProductService } from "../product.service";
import { CreateProductRequest } from '../request/create-product.request';
import { CreateProductResponse } from '../response/create-product.response';
import { Product } from 'src/app/model/product.model';
import { UpdateProductRequest } from '../request/update-product.request';
import { UpdateProductResponse } from '../response/update-product.response';
import { FilterOptions } from '../request/filter-options.request';

@Injectable({
    providedIn: 'root'
})

export class ProductRestApiService extends ProductService {

    constructor(private httpService: HttpService) {
        super();
    }

    saveProduct(request: CreateProductRequest): Observable<CreateProductResponse> {

        const url: string = `${environment.api.host}/api/products`;

        return this.httpService.post(url, request, true).pipe(
            map((response: any) => response as CreateProductResponse)
        )

    }

    findAllProducts( filter?: FilterOptions ): Observable<Product[]> {

        let url: string = `${environment.api.host}/api/products`;

        if(filter) {
            url += '?';
            let previousFilter: boolean = false;

            if(filter.franchiseId) {
                url += `franchiseId=${filter.franchiseId}`;
                previousFilter = true;
            }

            if(filter.categoryId) {
                url += `${ previousFilter ? '&': ''}categoryId=${filter.categoryId}`;
                previousFilter = true;
            }

        }

        console.log('Products:' + url);

        return this.httpService.get(url, true).pipe(
            map((response: any) => response.products as Product[])
        );

    }

    findById(productId: string): Observable<Product> {

        const url: string = `${environment.api.host}/api/products/${productId}`;

        return this.httpService.get(url, true);

    }

    updateProduct(productId: string, request: UpdateProductRequest): Observable<UpdateProductResponse> {

        const url: string = `${environment.api.host}/api/products/${productId}`;

        return this.httpService.put(url, request, true).pipe(
            map( (response: any) => of(new UpdateProductResponse()) )
        );

    }

}