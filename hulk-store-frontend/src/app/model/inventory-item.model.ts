import { Product } from "./product.model";

export class InventoryItem {

    public product: Product;
    public incomings: number;
    public outgoings: number;

    public inStock(): number {
        return this.incomings - this.outgoings;
    }

}