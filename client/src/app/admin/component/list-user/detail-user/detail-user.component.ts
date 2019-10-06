import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, FormArray, AbstractControl } from '@angular/forms';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-detail-user',
  templateUrl: './detail-user.component.html',
  styleUrls: ['./detail-user.component.scss']
})
export class DetailUserComponent implements OnInit {
  userForm: FormGroup;
  dataSource = new BehaviorSubject<AbstractControl[]>([]);
  displayColumns = ['chargeName', 'price'];
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
      id: [null],
      educations: this.fb.array([ this.thongTinChinhChi() ]),
      inforDetails: this.fb.array([ this.hocvan() ]),
      charges: this.fb.array([ this.mucphi() ]),
    });
    this.service.DetailUser({userId: id}).subscribe(res => {
      console.log(res);
      this.userForm.patchValue(res);
      this.userForm.setControl('charges', this.fb.array(res.charges || []));
      this.userForm.setControl('inforDetails', this.fb.array(res.inforDetails || []));
      this.userForm.setControl('educations', this.fb.array(res.educations || []));
      console.log(this.userForm);
      // this.dataSource.next(this.userForm.get('charges').contro);
      console.log(this.userForm.get('charges'));
      
    })
  }
  mucphi() {
    return this.fb.group({
      chargeName: [null],
      price: [null],
    })
  }
  thongTinChinhChi() {
    return this.fb.group({
      level: [null],
      place:[null],
      time: [null],
    })
  }
  hocvan() {
    return this.fb.group({
      certificate: [null],
      placeOfIssue: [null],
      yearOfIssue: [null],
    })
  }
  onSubmit() {

  }
}
