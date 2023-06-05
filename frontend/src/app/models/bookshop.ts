import { Book } from "./book";

export interface BookShop {
    id:number;
    title:String;
    location:String;
    books:Book[]
    contactNo:String;
    email:String;
}
