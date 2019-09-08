import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import {Subscription} from 'rxjs';
import {AuthenticationService} from './authentication.service';
import { environment } from '@environments/environment';
import {UserService} from './user.service';

@Injectable({providedIn: 'root'})
export class ChatService {

  stompClient = null;
  connectRoom: Subscription;
  constructor(
    private auth: AuthenticationService,
    private userService: UserService,
  ) { }

  connect(onConnected, onError) {
    if (!this.stompClient) {
      const socket = new SockJS(environment.ws);
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect({}, onConnected, onError);
    }
  }

  createDestination(roomId, onMessageReceived) {
    this.connectRoom = this.stompClient.subscribe(`/topic/${roomId}`, onMessageReceived);
  }

  disConnect() {
    if (this.connectRoom) {
      this.connectRoom.unsubscribe();
    }
  }

  sendMessage(roomId, message) {
    if (roomId) {
      const chatMessage = {
        sender: this.auth.getCurrentUser().name,
        content: message,
        type: 'CHAT',
        roomId: roomId,
        serverUserId: this.auth.getCurrentUser().id
      };
      this.stompClient.send(`/app/chat/${roomId}/sendMessage`, {}, JSON.stringify(chatMessage));
    }
  }

  join(roomId) {
    if (this.auth.isAdmin()) {
      this.stompClient.send(`/app/chat/${roomId}/addUser`, {},
        JSON.stringify({serverUserId: this.auth.getCurrentUser().id, type: 'JOIN'}));
    } else {
      this.stompClient.send(`/app/chat/${roomId}/addUser`, {},
        JSON.stringify({sender: this.auth.getCurrentUser().name, type: 'JOIN'}));
    }
  }

  getServerUserImage(userId): string {
    if(userId) {
      let image = localStorage.getItem("image_user_" + userId);
      if(!image) {
        this.userService.getImageByUserId(userId)
          .subscribe(data => {
            image = data;
            localStorage.setItem("image_user_" + userId, image);
          });
      }
      return image;
    }
    return '';
  }
}
