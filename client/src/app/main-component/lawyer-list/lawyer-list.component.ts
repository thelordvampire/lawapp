import { Component, OnInit } from '@angular/core';
import {UserService} from '../../_services';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-lawyer-list',
  templateUrl: './lawyer-list.component.html',
  styleUrls: ['./lawyer-list.component.scss']
})
export class LawyerListComponent implements OnInit {

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.router.routeReuseStrategy.shouldReuseRoute = function(){
      return false;
    }
  }

  listUser;

  ngOnInit() {
    const type = this.route.snapshot.paramMap.get('type');
    this.userService.getByType(type).subscribe(res => {
      this.listUser = res;
    });
  }

}
