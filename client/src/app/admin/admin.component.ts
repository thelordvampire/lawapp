import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { AuthenticationService } from '../_services';
import { Router } from '@angular/router';
import { AppService } from '../app.service';
import { ChatComponent } from '../main-component/chat/chat.component';
import {ChatService} from '../_services/chat.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit, AfterViewInit {
  @ViewChild(ChatComponent) appChat: ChatComponent;
  DEFAULDATA = {
    name: 'Admin',
    message: 'Xin chào'
  };
  selectedId: any;
  data: Object;
  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private appService: AppService,
    ) {

    }
    currentUser = null;

  ngOnInit() {
    this.appService.setHeader(false);
    this.getAllUserChat();
    this.currentUser = this.authenticationService.getCurrentUser();
  }

  getAllUserChat() {
    this.getUser();
    setInterval(() => { this.getUser(); }, 5 * 1000);
  }
  ngAfterViewInit() {
    // child is set
    // this.appChat.enterRoom();
  }
  getUser() {
    this.appService.GetListUserChat().subscribe(res => {
      this.data = res.reverse();
    });
  }
  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
    if (window.location.href.indexOf('reload') == -1) {
      window.location.replace(window.location.href + '?reload');
   }
  }

  activeRoomId = null;
  listEnterRoomId = [];
  onPressOpenChatBox(id) {
    this.selectedId = id;
    this.getUser();
    if (this.activeRoomId !== id) {
      this.activeRoomId = id;
      this.appService.setOpenChatBox(id);
    }
    if (this.listEnterRoomId.find(item => item === id)) {
      this.appChat.enterRoom(id);
    } else {
      this.listEnterRoomId.push(id);
      this.appChat.enterRoom(id);
    }

    // this.appChat.disConnect();
  }

  closeRoom(roomId, $event) {
    // thong baos message;
    $event.stopPropagation();
    if (true) {
      this.appService.closeRoom(roomId).subscribe((res: any) => {
        console.log(res);
      });
    }
  }
}
