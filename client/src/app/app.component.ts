import { Component, OnInit, HostListener } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';

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
       this.auth.currentUser.subscribe(x => this.currentUser = x);
    }
    ngOnInit() {
        this.appService.getHeader.pipe(debounceTime(100)).subscribe(output => {
            this.isLogin = output;
        });
        this.auth.validateTokenExpirationDate();
        this.appGoToTop();
    }
    logout() {
        this.auth.logout();
        this.router.navigate(['/login']);
    }
    appGoToTop() {
        this.router.events.subscribe((evt) => {
            if (!(evt instanceof NavigationEnd)) {
                return;
            }
            window.scrollTo(0, 0)
        });
    }
}
