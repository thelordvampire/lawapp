import { Component, OnInit } from '@angular/core';
import { AppService } from '../../app.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import {cloneDeep} from 'lodash';
import {Observable, Subscription} from 'rxjs';
import {ChatService} from '../../_services/chat.service';
import {AuthenticationService} from '../../_services';
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
  isShowChatDialog = true;

  isAdmin = false;
  usernamePage;
  chatPage;
  usernameForm;
  messageForm;
  messageInput;
  messageArea;
  connectingElement;
  title: any = 'Chat với chúng tôi';
  roomId: Object;
  chatHistory: any;
  currentChat: any;

  constructor(
    private appService: AppService,
    private fb: FormBuilder,
    private router: ActivatedRoute,
    private chatService: ChatService,
    private auth: AuthenticationService,
    ) {}

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
      // this.isAdmin = true;
      this.isShowChatDialog = false;
    }
  }
    OpentChatBox() {
    this.appService.getChatBox.subscribe(res => {
      this.isShowHeader = false;
    });
  }
  getChatBoxUser() {
    if (localStorage.getItem('UserRoom')) {
      this.appService.GetListUserChat().subscribe(res => {
        this.enterRoom(JSON.parse(localStorage.getItem('UserRoom')), res.reverse());
        this.isShowHeader = false;
      });
    }
  }

 username = null;

 colors = [
  '#2196F3', '#32c787', '#00BCD4', '#ff5652',
  '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

  createRoom() {
   // client call
    debugger;
   if (this.chatForm.controls.Username.value.trim() == '') {
     return;
   }
   const data = { clientUserName: this.chatForm.controls.Username.value };
   this.appService.CreateRoom(data).subscribe((res: any) => {
     debugger;
     this.roomId = res.id;
     this.auth.createUser(res.clientUserName);
     this.enterRoom();
    });
}

  enterRoom(roomId?, chatHistory?) {
    console.log('enter room');
    this.roomId = roomId ? roomId : this.roomId;
    this.chatService.connect(this.onConnected.bind(this), this.onError.bind(this));

    this.isShowChatDialog = true;
    this.isShow = true;
    this.title = 'Chào ' + this.auth.getCurrentUser().username;
    this.isAdmin = this.auth.isAdmin();

    $('#messageArea').html('');


    // if (chatHistory) {
    //   this.currentChat = chatHistory.find(el => el.id == roomId);
    //   this.chatForm.patchValue({
    //     Username: this.currentChat.clientUserName
    //   });
    // }
  }

 onConnected() {
   this.chatService.createDestination(this.roomId, this.onMessageReceived.bind(this));
   if (this.router.snapshot && this.router.snapshot.routeConfig && this.router.snapshot.routeConfig.path == 'admin') {
     // this.isAdmin = true;
     // this.chatForm.patchValue({ Username: 'Quản trị viên'});
   }
   localStorage.setItem('UserRoom', JSON.stringify(this.roomId));
   this.isShow = true;
   let a = new Observable(() => {
     this.getHistory(this.roomId);
   }).subscribe(() => {
     debugger;
     this.chatService.join();
   });

}
  getHistory(roomId) {
    this.appService.GetHistoryRoomChat(roomId).subscribe(res => {
    console.log(res);
    if (res && res.length > 0 ) {
      // const data = cloneDeep(res);
      for (let i = 0; i < res.length; i++) {
        const content = {body: JSON.stringify(res[i])};
        this.onMessageReceived(content);
      }
          // if (i == data.length) {
          //   clearInterval(interval);
          // }
    }});
  }

 sendMessage() {
   debugger;
   const sender = this.auth.getCurrentUser().username;
   const message = this.chatForm.controls.Message.value;
   this.chatService.sendMessage(sender, message);
   this.chatForm.controls.Message.reset();
 }

 onMessageReceived(payload) {
    debugger;
   console.log('payload', payload);
   if (payload.body) {
     const message = JSON.parse(payload.body);
      // var message =  this.currentChat;
     console.log('message', message);
     const messageElement = document.createElement('li');

     if (message.type === 'JOIN') {
      messageElement.classList.add('event-message');
      message.content = message.sender + ' đã tham gia!';
     } else if (message.type === 'LEAVE') {
       messageElement.classList.add('event-message');
       message.content = message.sender + ' đã rời khỏi!';
     } else {
       messageElement.classList.add('chat-message');
       if (this.chatForm.controls.Username.value == message.sender) {
            // if(this.chatForm.controls.Username.value == this.currentChat.sender) {
         messageElement.classList.add('right-text');
       } else {
         messageElement.classList.add('left-text');
       }

       const avatarElement = document.createElement('i');
       const avatarText = document.createTextNode(message.sender[0]);
       avatarElement.appendChild(avatarText);
       avatarElement.style['background-color'] = this.getAvatarColor(message.sender);

       messageElement.appendChild(avatarElement);

       const usernameElement = document.createElement('span');
       const usernameText = document.createTextNode(message.sender);
       usernameElement.appendChild(usernameText);
       messageElement.appendChild(usernameElement);
     }

     const textElement = document.createElement('p');
     const messageText = document.createTextNode(message.content);
     textElement.appendChild(messageText);

     messageElement.appendChild(textElement);
     $('#messageArea').append(messageElement);
     $('#messageArea').scrollTop = $('#messageArea').scrollHeight;
   }
  }

 getAvatarColor(messageSender) {
  let hash = 0;
  for (let i = 0; i < messageSender.length; i++) {
      hash = 31 * hash + messageSender.charCodeAt(i);
  }
  const index = Math.abs(hash % this.colors.length);
  return this.colors[index];
}
onPressCloseHeader() {
  this.isShowHeader = !this.isShowHeader;
  // if (this.isShowHeader == false) {
  //     // this.getChatBoxUser();
  //   } else {
  //     // this.disConnect();
  //   }
  }

  onError(error) {
    console.log('on err');
    if (this.connectingElement) {
      this.connectingElement.textContent = 'Mất kết nối. Hãy tải lại trang và thử lại!';
      this.connectingElement.style.color = 'red';
    }
  }
}
