import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PcHawkComponent } from './pc-hawk.component';

describe('PcHawkComponent', () => {
  let component: PcHawkComponent;
  let fixture: ComponentFixture<PcHawkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PcHawkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PcHawkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
