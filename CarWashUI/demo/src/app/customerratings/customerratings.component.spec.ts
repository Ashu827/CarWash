import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerratingsComponent } from './customerratings.component';

describe('CustomerratingsComponent', () => {
  let component: CustomerratingsComponent;
  let fixture: ComponentFixture<CustomerratingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerratingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerratingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
