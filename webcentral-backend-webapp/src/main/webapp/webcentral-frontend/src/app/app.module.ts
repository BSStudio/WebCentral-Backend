import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';

@NgModule({
   declarations: [
      AppComponent,
      NavbarComponent
   ],
   imports: [
      BrowserModule,
      AppRoutingModule,
      BrowserAnimationsModule,
      MatIconModule,
      MatFormFieldModule,
      MatSelectModule
   ],
   providers: [],
   bootstrap: [
      AppComponent
   ]
})

export class AppModule { }
