import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProviderbookingComponent } from './providerbooking.component';

describe('ProviderbookingComponent', () => {
  let component: ProviderbookingComponent;
  let fixture: ComponentFixture<ProviderbookingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProviderbookingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProviderbookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
