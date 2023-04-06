import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

import { environment } from '../../../environments/environment';
import { LoginRequest } from '../request/login.request';
import { LoginResponse } from '../response/login.response';
import { LoginService } from "../login.service";
import { HttpService } from './http.service';
import { CreateUserRequest } from '../request/create-user.request';
import { CreateUserResponse } from '../response/create-user.response';

@Injectable({
    providedIn: 'root'
})

export class LoginRestApiService extends LoginService {

    constructor(
        private httpService: HttpService
    ) {
        super();
    }

    public login(request: LoginRequest): Observable<LoginResponse> {

        const url: string = `${environment.api.host}/api/auth/login`;

        return this.httpService.post(url, request).pipe(
            map((response: any) => response as LoginResponse)
        )

    }

    signUp(request: CreateUserRequest): Observable<CreateUserResponse> {

        const url: string = `${environment.api.host}/api/auth/sign-up`;

        return this.httpService.post(url, request).pipe(
            map((response: any) => response as CreateUserResponse)
        );

    }

}