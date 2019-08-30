import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { AuthenticationService } from '../_services';
import { Router } from '@angular/router';
import { AppService } from '../app.service';
import { ChatComponent } from '../main-component/chat/chat.component';

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
  }
  selectedId: any;
  data: Object;
  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private appService: AppService
    ) { 
      
    }

  ngOnInit() {
    this.appService.setHeader(false);
    this.getAllUserChat();
  }

  getAllUserChat() {
    this.getUser();
    setInterval(() => {
      this.getUser();
    }, 30 * 1000);
  }
  ngAfterViewInit() {
    // child is set
    // this.appChat.enterRoom();
  }
  getUser() {
    this.appService.GetListUserChat().subscribe(res => {
      this.data = res.reverse();
    })
  }
  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
    if (window.location.href.indexOf('reload')==-1) {
      window.location.replace(window.location.href+'?reload');
   }
  } 
  onPressOpenChatBox(id) {
    this.selectedId = id;
    this.getUser();
    this.appService.setOpenChatBox(id);
    this.appChat.enterRoom(id, this.data);
    this.appChat.disConnect();
  }
}
