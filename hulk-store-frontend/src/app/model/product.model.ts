import { Category } from './category.model';
import { Franchise } from './franchise.model';

export class Product {

    id: string;
    code: string;
    name: string;
    purchasePrice: number;
    sellingPrice: number;
    category: Category;
    franchise: Franchise;

    constructor() {}

}