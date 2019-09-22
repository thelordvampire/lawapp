import { Component, OnInit } from '@angular/core';
import {UserService} from '../../_services';

@Component({
  selector: 'app-lawyer-list',
  templateUrl: './lawyer-list.component.html',
  styleUrls: ['./lawyer-list.component.scss']
})
export class LawyerListComponent implements OnInit {

  constructor(
    private userService: UserService
  ) { }

  listUser;

  ngOnInit() {
    this.userService.getAll().subscribe(res => {
      console.log(res);
      this.listUser = res;
    });
  }

}
