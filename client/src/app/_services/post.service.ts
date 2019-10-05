import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  getById(postId) {
    return this.http.get<any[]>(`${environment.apiUrl}/post/${postId}`);
  }

  getMyPost(page) {
    console.log(page);
    return this.http.get<any[]>(`${environment.apiUrl}/post/my`, page);
  }

  update(post) {
    return this.http.put<any[]>(`${environment.apiUrl}/post/update`, post);
  }

  create(post) {
    return this.http.post(`${environment.apiUrl}/post/create`, post);
  }

  getAll() {
    return this.http.get(`${environment.apiUrl}/post/get-all`);
  }

}
