import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2'

import { RoutingService } from 'src/app/routing/routing.service';
import { LoginService } from 'src/app/services/login.service';
import { CreateUserRequest } from 'src/app/services/request/create-user.request';
import { CreateUserResponse } from 'src/app/services/response/create-user.response';

@Component({
    selector: 'create-user',
    templateUrl: './create-user.component.html'
})

export class CreateUserComponent implements OnInit {

    formSubmitted: boolean = false;
    loading: boolean = false;

    createUserForm = new FormGroup({
        username: new FormControl('', [Validators.required]),
        password: new FormControl('', [Validators.required]),
        confirmPassword: new FormControl('', [Validators.required]),
        firstName: new FormControl('', [Validators.required]),
        lastName: new FormControl('', [Validators.required])
    });

    constructor(
        private routingService: RoutingService,
        private loginService: LoginService
    ) {

    }

    ngOnInit() {

    }

    signUp(): void {

        this.formSubmitted = true;

        if( this.createUserForm.valid ) {

            if( this.passwordsAreEqual() ) {

                this.loading = true;

                const request: CreateUserRequest = this.getRequest();
                
                this.loginService.signUp(request).subscribe(
                    (response: CreateUserResponse) => {

                        Swal.fire(
                            'Usuario creado',
                            'Usuario creado exitosamente',
                            'success'
                        )

                        this.routingService.goToLogin();

                    }
                ).add( () => this.loading = false );

            } else {

                Swal.fire(
                    'Error',
                    'Las contraseñas ingresadas son son iguales',
                    'error'
                )

            }



        }

    }

    goHome(): void {
        this.routingService.goToLogin();
    }

    isEmpty(fieldName: string): boolean {
        return this.createUserForm.get(fieldName).hasError('required') 
                && (this.createUserForm.get(fieldName).touched || this.formSubmitted)
    }

    private getRequest(): CreateUserRequest {

        let request: CreateUserRequest = new CreateUserRequest();
        request.username = this.createUserForm.get('username').value;
        request.password = this.createUserForm.get('password').value;
        request.firstName = this.createUserForm.get('firstName').value;
        request.lastName = this.createUserForm.get('lastName').value;

        return request;

    }

    private passwordsAreEqual(): boolean {

        const password: string = this.createUserForm.get('password').value;
        const confirmPassword: string = this.createUserForm.get('confirmPassword').value;

        return password === confirmPassword;

    }

}