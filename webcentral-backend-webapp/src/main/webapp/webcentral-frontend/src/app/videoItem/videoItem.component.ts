import { ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

import { VideoItemService, VideoItem, ResponseVideoItem } from './videoItem.service';

@Component({
  selector: 'wc-video-item',
  templateUrl: './videoItem.component.html',
  styleUrls: ['./videoItem.component.css']
})
export class VideoItemComponent implements OnInit {

  @ViewChild('player')
  videoPlayer: ElementRef;
  
  private route: ActivatedRoute;
  private videoItemService: VideoItemService;
  video: VideoItem;

  constructor(videoItemService: VideoItemService, route: ActivatedRoute) {
    this.videoItemService = videoItemService;
    this.route = route;
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    console.log('id: ' + id);
    this.videoItemService.getVideoItem(id).subscribe((response: ResponseVideoItem) => {
      this.video = response.video;
      this.videoPlayer.nativeElement.load();
    });
  }

}
