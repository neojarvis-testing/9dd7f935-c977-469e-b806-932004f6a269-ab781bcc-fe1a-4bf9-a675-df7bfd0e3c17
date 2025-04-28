import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Api } from 'src/api';
import { User } from '../models/user.model';
import { LoginDto } from '../models/loginDTO.model';
import { Login } from '../models/login.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
   
  constructor(private http:HttpClient) { }
  
}
