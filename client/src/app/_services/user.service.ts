import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<any[]>(`${environment.apiUrl}/user/get-lawer`);
    }

    getById(id) {
      return this.http.get<any[]>(`${environment.apiUrl}/user/${id}`);
    }

    register(user) {
        return this.http.post(`${environment.apiUrl}/user/create`, user);
    }

    delete(id) {
        return this.http.delete(`${environment.apiUrl}/user/${id}`);
    }
    getAllRole() {
        return this.http.get<any>(`${environment.apiUrl}/role/get-all`);
    }
}
