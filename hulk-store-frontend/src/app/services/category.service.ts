import { Observable } from "rxjs";
import { Category } from "../model/category.model";

export abstract class CategoryService {
    
    abstract findAllCategories(): Observable<Category[]>

}