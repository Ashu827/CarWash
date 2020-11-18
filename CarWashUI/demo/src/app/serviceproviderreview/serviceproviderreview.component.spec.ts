import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceproviderreviewComponent } from './serviceproviderreview.component';

describe('ServiceproviderreviewComponent', () => {
  let component: ServiceproviderreviewComponent;
  let fixture: ComponentFixture<ServiceproviderreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServiceproviderreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceproviderreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
