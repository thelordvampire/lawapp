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
  data: Object;

//   data = [
//     {
//     id: 1,
//     user: 'Nguyễn Văn A'
//   },
//   {
//     id: 2,
//     user: 'Đoàn Tuấn B'
//   },
//   {
//     id: 3,
//     user: 'Nguyễn Thị D'
//   },
//   {
//     id: 4,
//     user: 'Cao Bá D'
//   },
//   {
//     id: 5,
//     user: 'Nguyễn Đức T'
//   },
//   {
//     id: 1,
//     user: 'Nguyễn Văn A'
//   },
//   {
//     id: 2,
//     user: 'Đoàn Tuấn B'
//   },
//   {
//     id: 3,
//     user: 'Nguyễn Thị D'
//   },
//   {
//     id: 4,
//     user: 'Cao Bá D'
//   },
//   {
//     id: 5,
//     user: 'Nguyễn Đức T'
//   },
// ]

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
    this.appService.GetListUserChat().subscribe(res => {
      this.data = res.reverse();
    })
    setInterval(() => {
      this.appService.GetListUserChat().subscribe(res => {
        this.data = res.reverse();
      })
    }, 60 * 1000);
  }
  ngAfterViewInit() {
    // child is set
    // this.appChat.enterRoom();
  }
  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
    if (window.location.href.indexOf('reload')==-1) {
      window.location.replace(window.location.href+'?reload');
   }
  } 
  onPressOpenChatBox(id) {
    console.log('Open chat box with', id);
    this.appService.setOpenChatBox(id);
    this.appChat.enterRoom(id, this.data);
  }
}
