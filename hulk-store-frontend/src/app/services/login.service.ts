import { Observable } from 'rxjs';

import { LoginRequest } from './request/login.request';
import { LoginResponse } from './response/login.response';
import { CreateUserRequest } from './request/create-user.request';
import { CreateUserResponse } from './response/create-user.response';

export abstract class LoginService {

    public abstract login(request: LoginRequest): Observable<LoginResponse>

    public abstract signUp(request: CreateUserRequest): Observable<CreateUserResponse>; 

}