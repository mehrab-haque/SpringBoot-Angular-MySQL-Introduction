import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BooksuiComponent } from './booksui.component';

describe('BooksuiComponent', () => {
  let component: BooksuiComponent;
  let fixture: ComponentFixture<BooksuiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BooksuiComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BooksuiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
