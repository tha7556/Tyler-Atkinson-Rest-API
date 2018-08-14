import { TestBed, inject } from '@angular/core/testing';

import { SalesmanService } from './salesman.service';

describe('SalesmanService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SalesmanService]
    });
  });

  it('should be created', inject([SalesmanService], (service: SalesmanService) => {
    expect(service).toBeTruthy();
  }));
});
