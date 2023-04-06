import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from 'src/app/services/login.service';
import { LoginRequest } from 'src/app/services/request/login.request';
import { LoginResponse } from 'src/app/services/response/login.response';

import { RoutingService } from '../../routing/routing.service';

@Component({
    selector: 'login',
    templateUrl: './login.component.html'
})

export class LoginComponent implements OnInit {

    loading: boolean = false;
    formSubmitted: boolean = false; 

    loginForm = new FormGroup({
        username: new FormControl('', [Validators.required]),
        password: new FormControl('', [Validators.required])
    });

    constructor(
        private loginService: LoginService,
        private routingService: RoutingService
    ) {

    }

    ngOnInit() {

    }

    login(): void {

        this.formSubmitted = true;

        if(this.loginForm.valid) {

            this.loading = true;

            const request: LoginRequest = this.getRequest();

            this.loginService.login(request).subscribe(
                (response: LoginResponse) => {

                    sessionStorage.setItem('token', response.token);

                    this.routingService.goToProductList();

                }
            )

        }

    }

    goToSignUp(): void {
        this.routingService.goToCreateUser();
    }

    isEmpty(fieldName: string): boolean {
        return this.loginForm.get(fieldName).hasError('required') 
                && (this.loginForm.get(fieldName).touched || this.formSubmitted)
    }

    private getRequest(): LoginRequest {
        let request: LoginRequest = new LoginRequest();
        request.username = this.loginForm.get('username').value;
        request.password = this.loginForm.get('password').value;
        return request;
    }

}