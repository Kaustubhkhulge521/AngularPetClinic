import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { registeruser } from './register-user';
import { registerDoctor } from './register-doctor';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class RegisterUserService {

  private baseUrl = 'http://localhost:8080/api/';
  private router: Router;
  constructor(private http:HttpClient) {
    
   }

  createPartner(registeruser: object): Observable<object> {
    //console.log("hi")
    return this.http.post(`${this.baseUrl}`+'save-owner', registeruser);
   
   }

    
    createDoctor(registerDoctor: object): Observable<object> {
     // console.log("hi1")
      return this.http.post(`${this.baseUrl}`+'save-doctor', registerDoctor);  }
  
  }
