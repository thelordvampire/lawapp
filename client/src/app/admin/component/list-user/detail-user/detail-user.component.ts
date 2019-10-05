import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-detail-user',
  templateUrl: './detail-user.component.html',
  styleUrls: ['./detail-user.component.scss']
})
export class DetailUserComponent implements OnInit {
  userForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private service: AppService,
  ) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.userForm = this.fb.group({
      name: [''],
      email: [''],
      phone: [''],
      introduce: [''],
      inforDetails: [null],
      educations: [null],
      charges: [null],
    });
    this.service.DetailUser({userId: id}).subscribe(res => {
      console.log(res);
      
    })
  }

  onSubmit() {

  }
}
