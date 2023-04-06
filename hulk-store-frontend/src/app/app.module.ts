import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import { ComponentsModule } from './components/components.module';
import { RouterModule } from '@angular/router';
import { RoutingModule } from './routing/routing.module';
import { ServicesModule } from './services/services.module';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    ComponentsModule,
    FormsModule,
    RouterModule,
    RoutingModule,
    ServicesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
