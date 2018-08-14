import { TestBed, inject } from '@angular/core/testing';

import { BackpropagationService } from './backpropagation.service';

describe('BackpropagationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BackpropagationService]
    });
  });

  it('should be created', inject([BackpropagationService], (service: BackpropagationService) => {
    expect(service).toBeTruthy();
  }));
});
