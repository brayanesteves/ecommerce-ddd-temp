import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { map } from 'rxjs/operators';

import { environment } from '../../../environments/environment';
import { Category } from '../../model/category.model';
import { CategoryService } from "../category.service";
import { HttpService } from './http.service';

@Injectable({
    providedIn: 'root'
})

export class CategoryRestApiService extends CategoryService {

    constructor(private httpService: HttpService) {
        super();
    }

    public findAllCategories(): Observable<Category[]> {

        const url = `${environment.api.host}/api/categories`;

        return this.httpService.get(url, true).pipe(
            map( (response: any) => response.categories as Category[] )
        )

    }

}