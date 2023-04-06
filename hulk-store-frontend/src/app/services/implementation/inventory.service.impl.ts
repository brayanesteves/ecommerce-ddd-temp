import { Injectable } from '@angular/core';
import { forkJoin, Observable } from "rxjs";
import { map } from "rxjs/operators";

import { environment } from '../../../environments/environment';
import { InventoryItem } from "../../model/inventory-item.model";
import { Product } from "../../model/product.model";
import { InventoryService } from "../inventory.service";
import { ProductService } from '../product.service';
import { HttpService } from './http.service';
import { InventoryInfo } from '../../model/inventory-info.model';
import { FilterOptions } from '../request/filter-options.request';

@Injectable({
    providedIn: 'root'
})

export class InventoryRestApiService extends InventoryService {

    constructor(
        private productService: ProductService,
        private httpService: HttpService    
    ) {
        super();
    }

    public findAllInventoryItems(filter?: FilterOptions): Observable<InventoryItem[]> {

        const allProducts$: Observable<Product[]> = this.productService.findAllProducts(filter);

        let url: string = `${environment.api.host}/api/inventory-info`;

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

        const allInventoryInfo$: Observable<InventoryInfo[]> = this.httpService.get(url, true).pipe(
            map( (response: any) => {
                return response.items as InventoryInfo[]
            })
        );

        const fork$: Observable<InventoryItem[]> = forkJoin([ allProducts$, allInventoryInfo$ ]).pipe(
            map(([ allProducts, allInventoryInfo ]) => {

                return allProducts.map( (product: Product) => {

                    const info: InventoryInfo = allInventoryInfo.find(i => i.productId === product.id);

                    let item: InventoryItem = new InventoryItem();
                    item.product = product;
                    item.incomings = info.incomings;
                    item.outgoings = info.outgoings;

                    return item;

                })

            })
        );

        return fork$;

    }

}