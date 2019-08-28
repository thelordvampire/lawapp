import { Component, OnInit, HostListener } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AuthenticationService } from './_services';
import { AppService } from './app.service';
import { debounceTime } from 'rxjs/operators';


@Component({ selector: 'app',
 templateUrl: 'app.component.html',
 styleUrls: ['./app.component.scss'] })
export class AppComponent implements OnInit {
    currentUser: any; 
    isLogin = true;

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService,
        private route: ActivatedRoute,
        private appService: AppService,
    ) {
      //  this.router.navigate(['/home']);
       this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
        
    }
    ngOnInit() {
        this.appService.getHeader.pipe(debounceTime(100)).subscribe(output => {
            console.log('out', output);
            this.isLogin = output;
        })

        this.authenticationService.getTokenExpirationDate(JSON.parse(localStorage.getItem('currentUser')).token);
    }
    logout() {
        this.authenticationService.logout();
        this.router.navigate(['/login']);
    }
} 