import { VideoItem } from './videoItem.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'wc-video-item',
  templateUrl: './videoItem.component.html',
  styleUrls: ['./videoItem.component.css']
})
export class VideoItemComponent implements OnInit {
  @Input() video: VideoItem;

  constructor() { }

  ngOnInit() {
  }

}
