/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { VideoItemService } from './videoItem.service';

describe('Service: VideoItem', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VideoItemService]
    });
  });

  it('should ...', inject([VideoItemService], (service: VideoItemService) => {
    expect(service).toBeTruthy();
  }));
});
