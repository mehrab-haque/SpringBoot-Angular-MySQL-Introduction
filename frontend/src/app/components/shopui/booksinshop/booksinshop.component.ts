import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Book } from 'src/app/models/book';

@Component({
  selector: 'app-booksinshop',
  templateUrl: './booksinshop.component.html',
  styleUrls: ['./booksinshop.component.css']
})
export class BooksinshopComponent implements OnInit {
  displayedColumns: string[] = ['title', 'author', 'genre', 'price', 'publisher', 'yearOfPublish'];
  dataSource!:Book[];
  constructor(public dialogRef: MatDialogRef<BooksinshopComponent>,
    @Inject(MAT_DIALOG_DATA) public data:Book[]) { }

  ngOnInit(): void {
    this.dataSource = this.data
  }

}
