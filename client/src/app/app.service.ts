import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AppService {
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
        this.$dataChatBox.next(data)
    }
    get getChatBox() {
        return this.$dataChatBox.asObservable();
    }
}