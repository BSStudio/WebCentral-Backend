import { VideoListComponent } from './videoList/videoList.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { VideoListModule } from './videoList/videoList.module';

const routes: Routes = [
  { path: 'video/all', component: VideoListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
