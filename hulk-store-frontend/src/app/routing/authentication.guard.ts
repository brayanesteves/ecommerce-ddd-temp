import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

import { RoutingService } from './routing.service';

@Injectable({
    providedIn: 'root'
})    

export class AuthenticationGuard implements CanActivate {

    constructor(
        private routingService: RoutingService
    ) {

    }

    canActivate(
        next: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree 
    {
    
        let token: string = sessionStorage.getItem('token');
        console.log(token);

        if(!token) {
            this.routingService.goToLogin();
        }

        return true;
    }

}