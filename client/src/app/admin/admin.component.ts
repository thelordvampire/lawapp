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

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private appService: AppService
    ) { 
      
    }

  ngOnInit() {
    this.appService.setHeader(false);
    this.appService.setDefaulChat(this.DEFAULDATA);
    
    
  }
  ngAfterViewInit() {
    // child is set
    this.appChat.connect();
  }
  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
    if (window.location.href.indexOf('reload')==-1) {
      window.location.replace(window.location.href+'?reload');
 }
}
}
