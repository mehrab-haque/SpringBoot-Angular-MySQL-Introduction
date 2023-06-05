import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { BooksService } from 'src/app/services/book/books.service';
import { MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-addbook',
  templateUrl: './addbook.component.html',
  styleUrls: ['./addbook.component.css']
})
export class AddbookComponent implements OnInit {
  bookForm!: FormGroup;
  constructor(public dialogRef: MatDialogRef<AddbookComponent>, private formBuilder: FormBuilder, private bookService:BooksService,private toastrService: ToastrService) { }

  ngOnInit(): void {
    this.createForm()
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
  
    this.bookService.add( this.bookForm.value).subscribe(
     { next: (v)=>{
        this.toastrService.success("Successfully Added", "Success")
        this.dialogRef.close();
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete') 
    })
   
  }

}
