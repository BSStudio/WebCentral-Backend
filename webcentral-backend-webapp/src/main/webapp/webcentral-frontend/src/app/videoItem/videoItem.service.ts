import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VideoItemService {

constructor() { }

}

export interface VideoItem {
  id: number;
  createdAt: string;
  longName: string;
  canonicalName: string;
  projectName: string;
  description: string;
  videoLocation: string;
  imageLocation: string;
}
