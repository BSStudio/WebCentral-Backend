
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { VideoComponent } from './video/video.component';
import { VideoListComponent } from './videoList/videoList.component';

const routes: Routes = [
  { path: 'video/all', component: VideoListComponent },
  { path: 'video/:id', component: VideoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
