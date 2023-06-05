import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BooksService } from 'src/app/services/book/books.service';
import {ToastrService } from 'ngx-toastr';
import { Book } from 'src/app/models/book';


@Component({
  selector: 'app-editbook',
  templateUrl: './editbook.component.html',
  styleUrls: ['./editbook.component.css']
})
export class EditbookComponent implements OnInit {
  bookForm!: FormGroup;
  constructor( public dialogRef: MatDialogRef<EditbookComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Book,private formBuilder: FormBuilder,
    private bookService:BooksService,private toastrService: ToastrService) { }

  ngOnInit(): void {
    //console.log(this.data)
    this.createForm()
    this.initializeFormWithData(this.data)
  }

  createForm(){
    this.bookForm = this.formBuilder.group({
      title: ["", Validators.required],
      price: ["", Validators.required],
      yearOfPublish: ["", Validators.required],
      author: ["", Validators.required],
      genre: ["", Validators.required],
      publisher: ["", Validators.required]
    });
  }

  onSubmit() {
    if (this.bookForm.invalid) {
      return;
    }
  
    this.bookService.edit(this.data.id, this.bookForm.value).subscribe(
     { next: (v)=>{
        this.toastrService.success("Successfully Edited", "Success")
        this.dialogRef.close();
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete') 
    })
   
  }

  initializeFormWithData(data: Book) {
    this.bookForm.patchValue(data);
  }

  

}
