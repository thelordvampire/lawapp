import { Component, OnInit, AfterViewInit, DoCheck } from '@angular/core';
import { first } from 'rxjs/operators';
import { AuthenticationService, UserService } from 'src/app/_services';
declare var $: any;


@Component({ selector: 'home',
templateUrl: 'home.component.html',
styleUrls: ['./home.component.scss'] })
export class HomeComponent implements OnInit, AfterViewInit,  DoCheck {
    currentUser: any;
    users = [];

    constructor(
        private authenticationService: AuthenticationService,
        private userService: UserService
    ) {
        this.currentUser = this.authenticationService.currentUserValue;
    }

    ngOnInit() {
      
    }
     ngAfterViewInit() {
        setTimeout(() => {
            $(window).load(function(){
                $('.flexslider').flexslider({
                  animation: "slide",
                  start: function(slider){
                    $('body').removeClass('loading');
                  }
                });
              }); 
        }, 10);  
     }
     ngDoCheck(){
     
    }
}