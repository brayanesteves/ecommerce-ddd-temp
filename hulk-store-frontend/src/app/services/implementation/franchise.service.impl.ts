import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { map } from 'rxjs/operators';

import { environment } from '../../../environments/environment';
import { Franchise } from '../../model/franchise.model';
import { FranchiseService } from "../franchise.service";
import { HttpService } from './http.service';

@Injectable({
    providedIn: 'root'
})

export class FranchiseRestApiService extends FranchiseService {

    constructor(private httpService: HttpService) {
        super();
    }

    findAllFranchises(): Observable<Franchise[]> {

        const url = `${environment.api.host}/api/franchises`;

        return this.httpService.get(url, true).pipe(
            map( (response: any) => response.franchises as Franchise[] )
        )

    }

}