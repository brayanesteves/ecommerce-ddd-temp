import { Component, Input, OnInit } from '@angular/core';
import { RoutingService } from 'src/app/routing/routing.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})

export class HeaderComponent implements OnInit {

    pageTitle: string = 'Gestión de inventario';
    @Input() subtitle;

    constructor(
        private routingService: RoutingService
    ) {

    }

    ngOnInit() {

    }

    logout(): void {
        sessionStorage.removeItem('token');
        this.routingService.goToLogin();
    }

}