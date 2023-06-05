import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BookShop } from 'src/app/models/bookshop';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  
  private shopUrl: string;
  constructor(private http: HttpClient) { 
    this.shopUrl = "http://localhost:8080/api/shops"
  }

  public findAll(): Observable<BookShop[]> {
    return this.http.get<BookShop[]>(this.shopUrl);
  }



  public edit(id:number, data:BookShop){
    return this.http.put<BookShop>(this.shopUrl+"/"+id, data)
  }

  public remove(id:number){
    return this.http.delete<BookShop>(this.shopUrl+"/"+id)
  }

  public add(data:BookShop){
    return this.http.post(this.shopUrl, data)
  }
}
