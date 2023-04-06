import { Observable } from "rxjs";
import { InventoryItem } from "../model/inventory-item.model";
import { FilterOptions } from './request/filter-options.request';

export abstract class InventoryService {

    public abstract findAllInventoryItems(filter?: FilterOptions): Observable<InventoryItem[]>;

}