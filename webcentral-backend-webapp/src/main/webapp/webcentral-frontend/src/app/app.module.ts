import { VideoListItemComponent } from './videoListItem/videoListItem.component';
import { VideoListComponent } from './videoList/videoList.component';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { VideoComponent } from './video/video.component';
import { VideoItemComponent } from './videoItem/videoItem.component';
import { VideoListComponent } from './videoList/videoList.component';

@NgModule({
   declarations: [
      AppComponent,
      NavbarComponent,
      VideoListComponent,
      VideoListItemComponent
      VideoComponent
   ],
   imports: [
      BrowserModule,
      AppRoutingModule,
      BrowserAnimationsModule,
      MatIconModule,
      MatFormFieldModule,
      MatSelectModule,
      RouterModule,
      HttpClientModule
   ],
   providers: [],
   bootstrap: [
      AppComponent
   ]
})

export class AppModule { }
