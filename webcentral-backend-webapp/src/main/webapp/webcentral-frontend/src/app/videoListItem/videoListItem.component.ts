import { VideoListItem } from './videoListItem.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'wc-video-list-item',
  templateUrl: './videoListItem.component.html',
  styleUrls: ['./videoListItem.component.css']
})
export class VideoListItemComponent implements OnInit {
  @Input() video: VideoListItem;

  constructor() { }

  ngOnInit() {
  }

}
