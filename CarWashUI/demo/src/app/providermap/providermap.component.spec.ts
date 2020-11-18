import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvidermapComponent } from './providermap.component';

describe('ProvidermapComponent', () => {
  let component: ProvidermapComponent;
  let fixture: ComponentFixture<ProvidermapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProvidermapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProvidermapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
