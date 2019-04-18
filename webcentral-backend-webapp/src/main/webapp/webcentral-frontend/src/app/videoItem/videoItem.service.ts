import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VideoItemService {

  private apiUrl = 'http://localhost:8080/wc/api/';
  private http: HttpClient;

  constructor(http: HttpClient) {
    this.http = http;
  }

  getVideoItem(id: string) {
    return this.http.get<ResponseVideoItem>(this.apiUrl + 'video/' + id);
  }

}

export interface ResponseVideoItem {
  video: VideoItem;
}

export interface VideoItem {
  id: number;
  archived: boolean;
  visible: boolean;
  // createdAt: string;
  longName: string;
  canonicalName: string;
  projectName: string;
  description: string;
  videoLocation: string;
  imageLocation: string;
}
