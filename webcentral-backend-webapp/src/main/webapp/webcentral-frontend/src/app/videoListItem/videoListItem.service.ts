import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VideoListItemService {

constructor() { }

}

export interface VideoListItem {
  id: number;
  createdAt: string;
  longName: string;
  canonicalName: string;
  projectName: string;
  description: string;
  videoLocation: string;
  imageLocation: string;
}
