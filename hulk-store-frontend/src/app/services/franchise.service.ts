import { Observable } from "rxjs";
import { Franchise } from "../model/franchise.model";

export abstract class FranchiseService {
    
    abstract findAllFranchises(): Observable<Franchise[]>

}