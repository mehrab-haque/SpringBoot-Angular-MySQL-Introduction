import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { ShopService } from 'src/app/services/shop/shop.service';
import { EditshopComponent } from '../editshop/editshop.component';
import { AddshopComponent } from '../addshop/addshop.component';
import { BooksinshopComponent } from '../booksinshop/booksinshop.component';
import { BookShop } from 'src/app/models/bookshop';

@Component({
  selector: 'app-listshop',
  templateUrl: './listshop.component.html',
  styleUrls: ['./listshop.component.css']
})
export class ListshopComponent implements OnInit {

  private shop!: BookShop;
  displayedColumns: string[] = ['title', 'location', 'email', 'contactNo', 'books', 'actions'];
  dataSource!: BookShop[];
  constructor(private shopService: ShopService, public dialog: MatDialog, private toastrService: ToastrService) { }

  ngOnInit(): void {
    this.getAllShops();
  }

  getAllShops() {
    this.shopService.findAll().subscribe({
      next: (v) => {
        this.dataSource = v
      },
      error: (e) => console.error(e),
      complete: () => console.info('complete')
    });
  }
  edit(row: BookShop) {
    const dialogRef = this.dialog.open(EditshopComponent, {
      data: row,
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getAllShops()
    });
  }
  remove(row: BookShop) {
    this.shopService.remove(row.id).subscribe({
      next: (v) => {
        console.log(v)
        this.toastrService.warning("Successfully Removed", "Removed")
        this.getAllShops()
      },
      error: (err) => {
        console.log(err)
        this.toastrService.error(err.error.error, "Error")
      },
    })
  }

  goAddNewShop() {
    const dialogRef = this.dialog.open(AddshopComponent);

    dialogRef.afterClosed().subscribe(result => {
      this.getAllShops()
    });
  }

  showBooks(element:BookShop){
    const dialogRef = this.dialog.open(BooksinshopComponent,{
      data:element.books,
      width:'100%'
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getAllShops()
    });
  }


}
