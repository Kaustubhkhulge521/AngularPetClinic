import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { registeruser } from './register-user';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})

export class StudentService {

  private baseUrl = 'http://localhost:8080/api/';

  constructor(private http:HttpClient) { }

  getStudentList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`+'pet-List');
  }

  createStudent(registeruser: object): Observable<object> {
  
    return this.http.post(`${this.baseUrl}`+'user-validation', registeruser);
    console.log();
  }

  deleteStudent(petId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/pet/${petId}`, { responseType: 'text' });
  }

  getStudent(petId: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/pet/${petId}`);
  }

  updateStudent(petId: number, student: object): Observable<Object> {
    console.log("Hi I m here" +student);
    return this.http.patch(`${this.baseUrl}pet/${petId}`, student);
  }
  
}                                           