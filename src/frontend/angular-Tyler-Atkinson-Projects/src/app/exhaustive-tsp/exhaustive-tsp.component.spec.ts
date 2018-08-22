import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExhaustiveTspComponent } from './exhaustive-tsp.component';

describe('ExhaustiveTspComponent', () => {
  let component: ExhaustiveTspComponent;
  let fixture: ComponentFixture<ExhaustiveTspComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExhaustiveTspComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExhaustiveTspComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
