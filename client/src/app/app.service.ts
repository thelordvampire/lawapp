import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject, Observable } from 'rxjs';
import { environment } from '@environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AppService {
    constructor(private http: HttpClient) {
    }

    $isShowHeader = new BehaviorSubject<any>(true);
    $defaulData = new Subject<any>();
    $dataChatBox = new Subject<any>();
    setHeader(isShow: boolean) {
        this.$isShowHeader.next(isShow);
    }
    get getHeader() {
        return this.$isShowHeader.asObservable();
    }

    setDefaulChat(data) {
        this.$defaulData.next(data);
    }
    get getDefaulChat() {
        return this.$defaulData.asObservable();
    }

    setOpenChatBox(data) {
        this.$dataChatBox.next(data);
    }
    get getChatBox() {
        return this.$dataChatBox.asObservable();
    }

    GetPostGetAll():any {
        const url = `${environment.apiUrl}/post/get-all`;
        return this.http.get(url);
    }
    GetPaging(data):any {
        const url = `${environment.apiUrl}/posts/paging`;
        return this.http.get(url, data);
    }
    GetPostId(data): Observable<any> {
        const url = `${environment.apiUrl}/post/${data}`;
        return this.http.get(url, data);
    }
    GetPostLimit(data): Observable<any> {
        const url = `${environment.apiUrl}/post/get-limit/${data}`;
        return this.http.get(url, data);
    }



    CreateRoom(data) {
        const url = `${environment.apiUrl}/chat/room/create/`;
        return this.http.post(url, data);
    }

    closeRoom(roomId) {
    const url = `${environment.apiUrl}/chat/room/force-close/${roomId}`;
    return this.http.get(url);
  }

    GetListUserChat(): Observable<any> {
        const url = `${environment.apiUrl}/chat/room/get-new`;
        return this.http.get(url);
    }

  GetHistoryRoomChat(roomId): Observable<any> {
    const url = `${environment.apiUrl}/chat/room/${roomId}/get-all`;
    return this.http.get(url);
  }
}
