import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BooksinshopComponent } from './booksinshop.component';

describe('BooksinshopComponent', () => {
  let component: BooksinshopComponent;
  let fixture: ComponentFixture<BooksinshopComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BooksinshopComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BooksinshopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
