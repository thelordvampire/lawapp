import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as jwt_decode from 'jwt-decode';
import { environment } from '@environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<any>;
    public currentUser: Observable<any>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue() {
        return this.currentUserSubject.value;
    }

    login(username, password) {
        return this.http.post<any>(`${environment.apiUrl}/user/login`, { username, password })
            .pipe(map(user => {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('currentUser', JSON.stringify(user));
                this.currentUserSubject.next(user);
                return user;
            }));
    }
    validateTokenExpirationDate(): Date {
      if (this.isAdmin()) {
        const token  = this.getCurrentUser().token;
        const currentTime = new Date();
        const decoded = jwt_decode(token);
        if (decoded.exp === undefined) {
          this.clearLocalStorage();
          return null;
        }
        const date = new Date(0);
        date.setUTCSeconds(decoded.exp);
        if (currentTime.getTime() >= date.getTime()) {
          this.clearLocalStorage();
        }
        return date;
      }
    }
    logout() {
        // remove user from local storage and set current user to null
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }

    clearLocalStorage() {
        localStorage.removeItem('UserRoom');
        localStorage.removeItem('currentUser');
    }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

  createUser(userName) {
      debugger;
    const currentUser = localStorage.getItem('currentUser');
    // if (!currentUser) {
      localStorage.setItem('currentUser', JSON.stringify({username: userName, isAdmin: false}));
    // }
  }

    isAdmin() {
      const user = this.getCurrentUser();
      return !!user.id;
      // return user ? user.isAdmin : null;
    }
}
