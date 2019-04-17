import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { VideoListItem } from '../videoListItem/videoListItem.service';

@Injectable({
  providedIn: 'root'
})
export class VideoListService {

  private apiUrl = 'http://localhost:8080/wc/api/';
  private http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  getVideoList(){
    return this.http.get<VideoList>(this.apiUrl + 'video/all');
  }

}

export interface VideoList {
  videos: VideoListItem[];
}
