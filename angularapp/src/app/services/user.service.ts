import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Api } from 'src/api';

@Injectable({
  providedIn: 'root'
})
export class UserService {
   apiUrl:string=Api.apiUrlUser
  constructor(private http:HttpClient) { }
}
