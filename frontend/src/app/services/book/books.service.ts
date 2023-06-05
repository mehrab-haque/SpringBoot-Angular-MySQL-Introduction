import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from 'src/app/models/book';


@Injectable({
  providedIn: 'root'
})
export class BooksService {
  private bookUrl: string;

  constructor(private http: HttpClient) { 
    this.bookUrl = 'http://localhost:8080/api/books';
    //edit url http://localhost:8080/api/books/5
    //post http://localhost:8080/api/books/
  }
  public findAll(): Observable<Book[]> {
    return this.http.get<Book[]>(this.bookUrl);
  }


  public edit(id:any, data:Book){
    return this.http.put<Book>(this.bookUrl+"/"+id, data)
  }

  public remove(id:number){
    return this.http.delete<number>(this.bookUrl+"/"+id)
  }

  public add(data:Book){
    return this.http.post(this.bookUrl+"/", data)
  }
}
