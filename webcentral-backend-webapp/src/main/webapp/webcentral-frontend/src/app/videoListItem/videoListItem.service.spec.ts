/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { VideoListItemService } from './videoListItem.service';

describe('Service: VideoItem', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VideoListItemService]
    });
  });

  it('should ...', inject([VideoListItemService], (service: VideoListItemService) => {
    expect(service).toBeTruthy();
  }));
});
