import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { VideoItemComponent } from './videoItem/videoItem.component';
import { VideoListComponent } from './videoList/videoList.component';

const routes: Routes = [
  { path: 'video/all', component: VideoListComponent },
  { path: 'video/:id', component: VideoItemComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
