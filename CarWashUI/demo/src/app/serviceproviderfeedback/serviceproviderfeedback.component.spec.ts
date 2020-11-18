import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceproviderfeedbackComponent } from './serviceproviderfeedback.component';

describe('ServiceproviderfeedbackComponent', () => {
  let component: ServiceproviderfeedbackComponent;
  let fixture: ComponentFixture<ServiceproviderfeedbackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceproviderfeedbackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceproviderfeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
