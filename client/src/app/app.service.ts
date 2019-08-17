import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AppService {
    $isShowHeader = new BehaviorSubject<any>(true);
    setHeader(isShow: boolean) {
        this.$isShowHeader.next(isShow);
    }
    get getHeader() {
        return this.$isShowHeader.asObservable();
    }
}