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
        private auth: AuthenticationService,
        private route: ActivatedRoute,
        private appService: AppService,
    ) {
      //  this.router.navigate(['/home']);
       this.auth.currentUser.subscribe(x => {
         // console.log('vao day roi', x);
         this.currentUser = x;
       });
    }
    ngOnInit() {
        this.appService.getHeader.pipe(debounceTime(100)).subscribe(output => {
            this.isLogin = output;
        });
        this.auth.validateTokenExpirationDate();

        console.log('user', this.auth.getCurrentUser());
    }
    logout() {
        this.auth.logout();
        this.router.navigate(['/login']);
    }
}
