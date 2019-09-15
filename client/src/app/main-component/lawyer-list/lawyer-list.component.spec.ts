import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LawyerListComponent } from './lawyer-list.component';

describe('LawyerListComponent', () => {
  let component: LawyerListComponent;
  let fixture: ComponentFixture<LawyerListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LawyerListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LawyerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
