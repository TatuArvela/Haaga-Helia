import { NgModule, LOCALE_ID }      from '@angular/core';
import { BrowserModule, Title }     from '@angular/platform-browser';
import { HttpModule, JsonpModule }  from '@angular/http';

import { AppRoutingModule }         from './app-routing.module';

import { AppComponent }  from './app.component';
import { MenuComponent }  from './menu.component';

@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    JsonpModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    MenuComponent
  ],
  providers: [
    { provide: LOCALE_ID, useValue: "fi-FI" },
    Title
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
