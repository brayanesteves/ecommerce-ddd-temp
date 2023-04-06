import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { Product } from '../model/product.model';

@Injectable({
  providedIn: 'root'
})

export class RoutingService {

  constructor(
    private router: Router
  ) {}

  goToCreateProduct(): void {
    this.navigateByURL('/create-product')
  }

  goToProductList(): void {
    this.navigateByURL('/products')
  }

  goToRegisterMovement(productInfo?: any): void {
    this.navigateByURL('/register-movement', productInfo);
  }

  goToMovementList(): void {
    this.navigateByURL('/movement-list')
  }

  goToEditProduct(product: Product) : void {
    this.navigateByURL('/create-product', product);
  }

  goToLogin(): void {
    this.navigateByURL('/login');
  }

  goToCreateUser(): void {
    this.navigateByURL('/sign-up');
  }

  navigateByURL(url: string, data? : any) {
    this.router.navigateByUrl(url, { state: data });
  }

  getNavigationData(): any {
    return this.router.getCurrentNavigation().extras.state;
  }

}