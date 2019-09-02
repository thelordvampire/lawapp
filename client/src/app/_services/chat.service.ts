import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import {Subscription} from 'rxjs';
import {AuthenticationService} from './authentication.service';
import { environment } from '@environments/environment';

@Injectable({providedIn: 'root'})
export class ChatService {

  stompClient = null;
  connectRoom: Subscription;
  id = null;
  constructor(
    private auth: AuthenticationService,
  ) { }

  connect(onConnected, onError) {
    if (!this.stompClient) {
      const socket = new SockJS(environment.ws);
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect({}, onConnected, onError);
    }
  }

  createDestination(roomId, onMessageReceived) {
    this.id = roomId;
    this.connectRoom = this.stompClient.subscribe(`/topic/${roomId}`, onMessageReceived);
  }

  disConnect() {
    if (this.connectRoom) {
      this.connectRoom.unsubscribe();
    }
  }

  sendMessage(userName, message) {
    if (this.id) {
      const chatMessage = {
        sender: userName,
        content: message,
        type: 'CHAT',
        roomId: this.id,
      };
      this.stompClient.send(`/app/chat/${this.id}/sendMessage`, {}, JSON.stringify(chatMessage));
    }
  }

  join() {
    if (this.auth.isAdmin()) {
      this.stompClient.send(`/app/chat/${this.id}/addUser`, {},
        JSON.stringify({serverUserId: this.auth.getCurrentUser().id, type: 'JOIN'}));
    } else {
      this.stompClient.send(`/app/chat/${this.id}/addUser`, {},
        JSON.stringify({sender: this.auth.getCurrentUser().username, type: 'JOIN'}));
    }
  }
}


// if (this.isAdmin) {
//   this.stompClient.send(`/app/chat/${this.roomId}/addUser`, {}, JSON.stringify({serverUserId: this.getCurrentUser(), type: 'JOIN'}));
// } else {
//   if (!localStorage.getItem('UserRoom')) {
//     this.stompClient.send(`/app/chat/${this.roomId}/addUser`, {}, JSON.stringify({
//       sender: this.chatForm.controls.Username.value,
//       type: 'JOIN'
//     }));
//   }
// }
