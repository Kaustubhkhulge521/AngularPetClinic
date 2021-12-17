import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewDiscriptionComponent } from './view-discription.component';

describe('ViewDiscriptionComponent', () => {
  let component: ViewDiscriptionComponent;
  let fixture: ComponentFixture<ViewDiscriptionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewDiscriptionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewDiscriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
