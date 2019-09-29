import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppService } from 'src/app/app.service';

@Component({
  selector: 'app-field',
  templateUrl: './field.component.html',
  styleUrls: ['./field.component.scss']
})
export class FieldComponent implements OnInit {
  data: any = {};
  constructor(
    private route: ActivatedRoute,
    private service: AppService,
  ) { }

  ngOnInit() {
    this.route.params.forEach(params => {
      if(params.id) {
        this.getData(params.id);
      }
    });
  }
  getData(id) {
    this.service.GetFieldId(id).subscribe(res => {
      this.data = res;
    })
  }
}
