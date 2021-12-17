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
  
  getDoctorList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`+'Doctor-List');
  }

  createDoctor(registeruser: object): Observable<object> {
  
    return this.http.post(`${this.baseUrl}`+'user-validation', registeruser);
    console.log();
  }
insertDoctor(registeruser: object): Observable<object> {
  
    return this.http.post(`${this.baseUrl}/save-doctor/`, { responseType: 'text' });
  }
  
  
  deleteDoctor(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/doctors/${id}`, { responseType: 'text' });
  }

  getDoctor(petId: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/doctors/${petId}`);
  }

  updateDoctor(id: number, value: any): Observable<Object> {
    return this.http.patch(`${this.baseUrl}/doctors/${id}`, value);
  }
  updateDoctor1(value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/doctors/`, value);
  }


  getOwnerList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`+'owner-List');
  }

  createOwner(registeruser: object): Observable<object> {
  
    return this.http.post(`${this.baseUrl}`+'user-validation', registeruser);
    console.log();
  }
  insertOwner(registeruser: object): Observable<object> {
  
    return this.http.post(`${this.baseUrl}/save-owner/`, { responseType: 'text' });
  }
  deleteOwner(petId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/owners/${petId}`, { responseType: 'text' });
  }

  getOwner(petId: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/owners/${petId}`);
  }

  updateOwner(petId: number, student): Observable<Object> {
    return this.http.patch(`${this.baseUrl}/owners/${petId}`, student);
  }

  updateOwner2(value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/owners/`, value);
  }

  getDescriptionList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`+'description-List');
  }

  createDescription(registeruser: object): Observable<object> {
  
    return this.http.post(`${this.baseUrl}`+'user-validation', registeruser);
    console.log();
  }

  deleteDescription(petId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/descriptions-Id/${petId}`, { responseType: 'text' });
  }

  getDescription(petId: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/description-List/${petId}`);
  }

  updateDescription(petId: number, student): Observable<Object> {
    return this.http.patch(`${this.baseUrl}/descriptions/${petId}`, student);
  }

  updateDescription2(value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/description-update/`, value);
  }

  insertPet(registeruser: object): Observable<object> {
  
    return this.http.post(`${this.baseUrl}/pet/`, { responseType: 'text' });
  }

  updatePet2(value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/pet-update/`, value);
  }

  deletePet(petId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/pet/${petId}`, { responseType: 'text' });
  }
  insertDescription(registeruser: object): Observable<object> {
  
    return this.http.post(`${this.baseUrl}/descriptions/`, { responseType: 'text' });
  }

}                                           