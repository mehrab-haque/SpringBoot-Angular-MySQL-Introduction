import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Book } from 'src/app/models/book';
import { BookShop } from 'src/app/models/bookshop';
import { BooksService } from 'src/app/services/book/books.service';
import { ShopService } from 'src/app/services/shop/shop.service';

@Component({
  selector: 'app-editshop',
  templateUrl: './editshop.component.html',
  styleUrls: ['./editshop.component.css']
})
export class EditshopComponent implements OnInit {
  shopForm!: FormGroup
  availableBooks!: Book[];
  constructor(public dialogRef: MatDialogRef<EditshopComponent>,
    @Inject(MAT_DIALOG_DATA) public data: BookShop, private formBuilder: FormBuilder,
    private shopService: ShopService, private toastrService: ToastrService, private bookService: BooksService) { }

  ngOnInit(): void {
    this.createForm()
    this.getAllBooks()
    this.initializeFormWithData(this.data)

  }
  createForm() {
    this.shopForm = this.formBuilder.group({
      title: ["", Validators.required],
      location: ["", Validators.required],
      contactNo: ["", Validators.required],
      email: ["", Validators.required],
      selectedBook: [""],
      books: this.formBuilder.array([

      ])
    });
  }
  newBook(): FormGroup {
    return this.formBuilder.group({
      title: ["", Validators.required],
      price: ["", Validators.required],
      yearOfPublish: ["", Validators.required],
      author: ["", Validators.required],
      genre: ["", Validators.required],
      publisher: ["", Validators.required]
    });
  }
  get books() {
    return this.shopForm.controls['books'] as FormArray
  }
  initializeFormWithData(data: BookShop) {
    this.shopForm.patchValue(data);

    if (data.books != null) {
      let booksControl = <FormArray>(
        this.shopForm.controls["books"]
      )

      data.books.forEach((book: Book) => {
        booksControl.push(
          this.formBuilder.group({
            title: book.title,
            price: book.price,
            yearOfPublish: book.yearOfPublish,
            author: book.author,
            genre: book.genre,
            publisher: book.publisher

          })
        )
      });
    }
  }

  addBook() {
    const selectedBookControl = this.shopForm.get('selectedBook');

    if (selectedBookControl && selectedBookControl.value) {
      const selectedBookId = selectedBookControl.value;

      const selectedBook = this.availableBooks.find((book: Book) => book.id == selectedBookId);

      if (selectedBook) {
        let booksControl = <FormArray>(
          this.shopForm.controls["books"]
        )
        booksControl.push(
          this.formBuilder.group({
            title: selectedBook.title,
            price: selectedBook.price,
            yearOfPublish: selectedBook.yearOfPublish,
            author: selectedBook.author,
            genre: selectedBook.genre,
            publisher: selectedBook.publisher

          })
        )

        const index = this.availableBooks.findIndex((book: Book) => book.id == selectedBookId);
        if (index !== -1) {
          this.availableBooks.splice(index, 1);
        }

        selectedBookControl.reset();
      } else {
        console.error('Selected book not found in available books list.');
      }
    }
  }

  getAllBooks() {
    this.bookService.findAll().subscribe({
      next: (v) => {
        this.availableBooks = v
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete')
    });
  }




  removeBook(index: number) {
    const booksArray = this.shopForm.get('books') as FormArray;
    const removedBook = booksArray.at(index).value;
    booksArray.removeAt(index);

    this.availableBooks.push(removedBook);
  }

  onSubmit() {
    if (this.shopForm.invalid) {
      return;
    }

    this.shopService.edit(this.data.id, this.shopForm.value).subscribe(
      {
        next: (v) => {
          console.log(v)
          this.toastrService.success("Successfully Edited", "Success")
          this.dialogRef.close();
        },
        error: (e) => console.error(e),
        complete: () => console.info('complete')
      })

  }

}
