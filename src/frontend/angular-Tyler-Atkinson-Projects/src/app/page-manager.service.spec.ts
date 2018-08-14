import { TestBed, inject } from '@angular/core/testing';

import { PageManagerService } from './page-manager.service';

describe('PageManagerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PageManagerService]
    });
  });

  it('should be created', inject([PageManagerService], (service: PageManagerService) => {
    expect(service).toBeTruthy();
  }));
});
