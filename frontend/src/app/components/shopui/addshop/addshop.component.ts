import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Book } from 'src/app/models/book';
import { BooksService } from 'src/app/services/book/books.service';
import { ShopService } from 'src/app/services/shop/shop.service';

@Component({
  selector: 'app-addshop',
  templateUrl: './addshop.component.html',
  styleUrls: ['./addshop.component.css']
})
export class AddshopComponent implements OnInit {

  shopForm!:FormGroup
  availableBooks!:Book[];
  constructor(public dialogRef:MatDialogRef<AddshopComponent>,private formBuilder:FormBuilder,private shopService:ShopService,private toastrService: ToastrService, private bookService:BooksService) { }

  ngOnInit(): void {
    this.createForm();
    this.getAllBooks();
  }

  getAllBooks(){
    this.bookService.findAll().subscribe({
      next: (v) => {
        this.availableBooks=v
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete') 
  });
  }
  createForm(){
    this.shopForm = this.formBuilder.group({
      title: ["", Validators.required],
      location: ["", Validators.required],
      contactNo: ["", Validators.required],
      email: ["", Validators.required],
      selectedBook:[""],
      books:  this.formBuilder.array([])
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

  addBook() {
    const selectedBookControl = this.shopForm.get('selectedBook');
  
    if (selectedBookControl && selectedBookControl.value) {
      const selectedBookId = selectedBookControl.value;
      
      // Find the selected book object from the availableBooks array
      const selectedBook = this.availableBooks.find((book: Book) => book.id==selectedBookId);
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
        // Remove the selected book from the available books list
        const index = this.availableBooks.findIndex((book: Book) => book.id == selectedBookId);
        if (index !== -1) {
          this.availableBooks.splice(index, 1);
        }
  
        // Reset the selectedBook form control
        selectedBookControl.reset();
      }
    }
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
   
    this.shopForm.removeControl("selectedBook")
    
  
    this.shopService.add( this.shopForm.value).subscribe(
     { next: (v)=>{
        
        this.toastrService.success("Successfully Added", "Success")
        this.dialogRef.close();
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete') 
    })
   
  }

}
