import { Component, OnInit } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { AppService } from '../../app.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import {cloneDeep} from 'lodash'
import { Subscription } from 'rxjs';
declare var $: any;

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.less']
})
export class ChatComponent implements OnInit {
  chatForm: FormGroup;
  isShow = false;
  isShowHeader = true;
  isAdmin = false;
  usernamePage;
  chatPage;
  usernameForm;
  messageForm;
  messageInput;
  messageArea;
  connectingElement;
  name: any = 'Contact with me';
  urlRoom: any;
  roomId: Object;
  chatHistory: any;
  currentChat: any;
  connectRoom: Subscription;
  constructor(
    private appService: AppService,
    private fb: FormBuilder,
    private router: ActivatedRoute
    ) {
     }

  ngOnInit() {
    this.chatForm = this.fb.group({
      Username: [''],
      Message: [''],
    });

    this.usernamePage = document.querySelector('#username-page');
    this.chatPage = document.querySelector('#chat-page');
    this.usernameForm = document.querySelector('#usernameForm');
    this.messageForm = document.querySelector('#messageForm');
    this.messageInput = document.querySelector('#message');
    this.messageArea = document.querySelector('#messageArea');
    this.connectingElement = document.querySelector('.connecting');
    this.OpentChatBox();
    if (this.router.snapshot && this.router.snapshot.routeConfig && this.router.snapshot.routeConfig.path == 'admin') {
      this.isAdmin = true;
    }
    this.getCurrentUser();
  }
  OpentChatBox() {
    this.appService.getChatBox.subscribe(res => {
      this.isShowHeader = false;
    })
  }
getChatBoxUser(){
  if (localStorage.getItem('UserRoom')) {
    this.appService.GetListUserChat().subscribe(res => {
      this.enterRoom(JSON.parse(localStorage.getItem('UserRoom')), res.reverse());
      this.isShowHeader = false;
    })
   
  }
}
 stompClient = null;
 username = null;

 colors = [
  '#2196F3', '#32c787', '#00BCD4', '#ff5652',
  '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];


 connect() {
  const data = {
    clientUserName: this.chatForm.controls.Username.value,
  }
    this.appService.CreateRoom(data).subscribe(res => {
      this.roomId = res['id'];
      this.enterRoom();
    })
}

  enterRoom(roomId?, chatHistory?) {
    $('#messageArea').html('');
    console.log('enter room');
    if (roomId) {
      this.roomId = roomId;
    }
    if (chatHistory) {
      this.currentChat = chatHistory.find(el => el.id == roomId);
      this.chatForm.patchValue({
        Username: this.currentChat.clientUserName
      });
    }
    const url = 'http://localhost:8080/ws';
    const socket = new SockJS(url);
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, this.onConnected.bind(this), this.onError.bind(this));
  }

 onConnected() {
  if (this.router.snapshot && this.router.snapshot.routeConfig && this.router.snapshot.routeConfig.path == 'admin') {
    this.isAdmin = true;
    this.chatForm.patchValue({
              Username: 'Admin',
            });
  } 
  localStorage.setItem('UserRoom', JSON.stringify(this.roomId));
  this.isShow = true;
   this.name = this.chatForm.controls.Username.value;
  // Subscribe to the Public Topic
  // Tell your username to the server
  this.connectRoom = this.stompClient.subscribe(`/topic/${this.roomId}`, this.onMessageReceived.bind(this))
    if (this.isAdmin) {
      this.stompClient.send(`/app/chat/${this.roomId}/addUser`, {}, JSON.stringify({serverUserId: this.getCurrentUser(), type: 'JOIN'}));
    } else {
      if (!localStorage.getItem('UserRoom')) {
        this.stompClient.send(`/app/chat/${this.roomId}/addUser`, {}, JSON.stringify({sender: this.chatForm.controls.Username.value, type: 'JOIN'}));
      }
    }
  this.getHistory();
}
getHistory() {
  if (this.currentChat && this.currentChat.listChatMessage.length > 0 ) {
      var i = 0;
      var data = cloneDeep(this.currentChat);
      var interval = setInterval(() =>{
      const content = {
          body: JSON.stringify(data.listChatMessage[i])
      }
      i++;
      this.onMessageReceived(content)
        
        if(i == data.listChatMessage.length) {
          clearInterval(interval);
        }
    }, 1);
}
}

 onError(error) {
    console.log('on err');
    if (this.connectingElement) {
      this.connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
      this.connectingElement.style.color = 'red';
    }
  }


 sendMessage() {
      var chatMessage = {
          sender: this.chatForm.controls.Username.value,
          content: this.chatForm.controls.Message.value,
          type: 'CHAT',
          roomId: this.roomId,
      };
      this.stompClient.send(`/app/chat/${this.roomId}/sendMessage`, {}, JSON.stringify(chatMessage));
      this.chatForm.controls.Message.reset();
}


 onMessageReceived(payload) {
   console.log('payload', payload);
if (payload.body) {
  var message = JSON.parse(payload.body);
  // var message =  this.currentChat;
  console.log('message', message);
    var messageElement = document.createElement('li');
  
    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');
  
        if(this.chatForm.controls.Username.value == JSON.parse(payload.body).sender) {
        // if(this.chatForm.controls.Username.value == this.currentChat.sender) {
  
          messageElement.classList.add('right-text');
        } else {
          messageElement.classList.add('left-text');
        }
  
        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = this.getAvatarColor(message.sender);
  
        messageElement.appendChild(avatarElement);
  
        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }
  
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
  
    messageElement.appendChild(textElement);
    $('#messageArea').append(messageElement);
    $('#messageArea').scrollTop = $('#messageArea').scrollHeight;
}

}


 getAvatarColor(messageSender) {
  var hash = 0;
  for (var i = 0; i < messageSender.length; i++) {
      hash = 31 * hash + messageSender.charCodeAt(i);
  }
  var index = Math.abs(hash % this.colors.length);
  return this.colors[index];
}
onPressCloseHeader() {
  this.isShowHeader = !this.isShowHeader;
  if (this.isShowHeader == false) {
      this.getChatBoxUser();
    } else {
      this.disConnect();
    }
  } 

  disConnect() {
    this.connectRoom.unsubscribe();
  }
   getCurrentUser() {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser) {
      return currentUser.id
    }
  }
}
