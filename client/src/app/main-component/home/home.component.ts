import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import { AuthenticationService, UserService } from 'src/app/_services';
import * as $ from 'jquery';


@Component({ selector: 'home',
templateUrl: 'home.component.html',
styleUrls: ['./home.component.scss'] })
export class HomeComponent implements OnInit {
    currentUser: any;
    users = [];

    constructor(
        private authenticationService: AuthenticationService,
        private userService: UserService
    ) {
        this.currentUser = this.authenticationService.currentUserValue;
    }

    ngOnInit() {
        $(document).ready(function () {
            console.log('xyyzzz');
            // $(window).load(function(){
                console.log('abc');
                $('.flexslider').flexslider({
                  animation: "slide",
                  start: function(slider){
                    $('body').removeClass('loading');
                  }
                });
              });
        // });
    }
}