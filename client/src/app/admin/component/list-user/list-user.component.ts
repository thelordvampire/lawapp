import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/app.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.scss']
})
export class ListUserComponent implements OnInit {
  dataTable: any[] = [];
  constructor(
    private service: AppService,
  ) { }

  ngOnInit() {
    this.getDataTable();
  }
  getDataTable() {
    this.service.GetUser().subscribe(res => {
      console.log(res);
      this.dataTable = res;
    });
  }

}
