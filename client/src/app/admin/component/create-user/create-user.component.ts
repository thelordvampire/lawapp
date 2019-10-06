import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AppService } from 'src/app/app.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent implements OnInit {
  createUser: FormGroup;
  constructor(
    private fb: FormBuilder,
    private service: AppService,
  ) { }

  ngOnInit() {
    this.createUser = this.fb.group({
      userName: ['', Validators.required],
      passWord: ['', Validators.required],
    });
  }

  onSubmit() {
    const formValue = this.createUser.value;
    if(this.createUser.invalid) {
      return false;
    }
    const data = {
      name: formValue.userName,
      passWord: formValue.passWord,
      id: null,
    }
    this.service.CreateUser(data).subscribe(res => {
    })
  }

}

