import { TestBed, inject } from '@angular/core/testing';

import { MarkovService } from './markov.service';

describe('MarkovService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MarkovService]
    });
  });

  it('should be created', inject([MarkovService], (service: MarkovService) => {
    expect(service).toBeTruthy();
  }));
});
