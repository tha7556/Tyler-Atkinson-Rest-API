import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BackpropagationNetworkComponent } from './backpropagation-network.component';

describe('BackpropagationNetworkComponent', () => {
  let component: BackpropagationNetworkComponent;
  let fixture: ComponentFixture<BackpropagationNetworkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BackpropagationNetworkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BackpropagationNetworkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
