import { Component, OnInit } from '@angular/core';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { AppService } from '../../app.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import $ from 'jquery';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.less']
})
export class ChatComponent implements OnInit {
  chatForm: FormGroup;
  constructor(
    private appService: AppService,
    private fb: FormBuilder,
    ) { }

  ngOnInit() {
    // this.initializeWebSocketConnection();
    this.appService.setHeader(false);
    this.chatForm = this.fb.group({
      Username: [''],
      Message: [''],
    });
  }
   usernamePage = document.querySelector('#username-page');
 chatPage = document.querySelector('#chat-page');
 usernameForm = document.querySelector('#usernameForm');
 messageForm = document.querySelector('#messageForm');
 messageInput = document.querySelector('#message');
 messageArea = document.querySelector('#messageArea');
 connectingElement = document.querySelector('.connecting');

 stompClient = null;
 username = null;

 colors = [
  '#2196F3', '#32c787', '#00BCD4', '#ff5652',
  '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

 connect() {
   console.log('document.querySelector', document.querySelector('#name'));
   // this.username = document.querySelector('#name').value.trim();

   // this.usernamePage.classList.add('hidden');
   // this.chatPage.classList.remove('hidden');
   const url = 'http://localhost:8080/ws';
   const socket = new SockJS(url);
   console.log('dfsdg', socket);


   this.stompClient = Stomp.over(socket);
   console.log('this.chatForm', this.stompClient);
   this.stompClient.connect({}, this.onConnected, this.onError);
  // event.preventDefault();
}


 onConnected() {
  // Subscribe to the Public Topic
  // this.stompClient.subscribe('/topic/public', onMessageReceived);

  // Tell your username to the server

  console.log('onConected');

  if (this.stompClient) {
    this.stompClient.send('/app/chat.addUser',
      {},
      JSON.stringify({sender: this.username, type: 'JOIN'})
    );
  }

  // this.connectingElement.classList.add('hidden');
}


 onError(error) {
   console.log('on err');

  this.connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
  // this.connectingElement.style.color = 'red';
}


 sendMessage() {
  //  var messageContent = this.messageInput.value.trim();
  const messageContent = 'fsdgds';
      var chatMessage = {
          sender: this.username,
          content: this.messageInput,
          type: 'CHAT'
      };
      this.stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
      // this.messageInput = '';
}


 onMessageReceived(payload) {
  var message = JSON.parse(payload.body);

  var messageElement = document.createElement('li');

  if(message.type === 'JOIN') {
      messageElement.classList.add('event-message');
      message.content = message.sender + ' joined!';
  } else if (message.type === 'LEAVE') {
      messageElement.classList.add('event-message');
      message.content = message.sender + ' left!';
  } else {
      messageElement.classList.add('chat-message');

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

  this.messageArea.appendChild(messageElement);
  this.messageArea.scrollTop = this.messageArea.scrollHeight;
}


 getAvatarColor(messageSender) {
  var hash = 0;
  for (var i = 0; i < messageSender.length; i++) {
      hash = 31 * hash + messageSender.charCodeAt(i);
  }
  var index = Math.abs(hash % this.colors.length);
  return this.colors[index];
}
// private serverUrl = 'https://localhost:8080/ws'
// private title = 'WebSockets chat';
// private stompClient;

// initializeWebSocketConnection(){
//   let ws = new SockJS(this.serverUrl);
//   this.stompClient = Stomp.over(ws);
//   let that = this;
//   this.stompClient.connect({}, function(frame) {
//     that.stompClient.subscribe("/chat", (message) => {
//       if(message.body) {
//         $(".chat").append("<div class='message'>"+message.body+"</div>")
//         console.log(message.body);
//       }
//     });
//   });
// }

// sendMessage(message){
//   this.stompClient.send("/app/send/message" , {}, message);
//   $('#input').val('');
// }
}
