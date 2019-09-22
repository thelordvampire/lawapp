import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '@environments/environment';

@Injectable({providedIn: 'root'})
export class FieldService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<any[]>(`${environment.apiUrl}/field/getAll`);
  }

  // register(user) {
  //   return this.http.post(`${environment.apiUrl}/user/create`, user);
  // }
  //
  // delete(id) {
  //   return this.http.delete(`${environment.apiUrl}/users/${id}`);
  // }
  // getAllRole() {
  //   return this.http.get<any>(`${environment.apiUrl}/role/get-all`);
  // }
}
