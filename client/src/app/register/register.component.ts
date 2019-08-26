import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { UserService, AuthenticationService, AlertService } from '../_services';
import { AppService } from '../app.service';

@Component({ templateUrl: 'register.component.html' ,  styleUrls: ['./register.component.scss'] })
export class RegisterComponent implements OnInit, OnDestroy {
    registerForm: FormGroup;
    loading = false;
    submitted = false;
    comparePass = false;
    roleList: any;
    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private authenticationService: AuthenticationService,
        private userService: UserService,
        private alertService: AlertService,
        private appService: AppService
    ) {
        // redirect to home if already logged in
        if (this.authenticationService.currentUserValue) {
            this.router.navigate(['/']);
        }
    }

    ngOnInit() {
        this.appService.setHeader(false);
        this.registerForm = this.formBuilder.group({
            email: ['', Validators.required],
            username: ['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(6)]],
            repassword: ['', [Validators.required, Validators.minLength(6)]],
            roleId: ['', Validators.required]
        });
        this.userService.getAllRole().subscribe(res => {
            console.log('ress', res);
            this.roleList = res;
        })
        this.f.password.valueChanges.subscribe(el => {
            this.comparePass = false;
        })
        this.f.repassword.valueChanges.subscribe(el => {
            this.comparePass = false;
        })
    }
    ngOnDestroy() {
        this.appService.setHeader(true);
    }
    // convenience getter for easy access to form fields
    get f() { return this.registerForm.controls; }

    onSubmit() {
        this.submitted = true;
        if(this.f.password.value != this.f.repassword.value) {
            this.comparePass = true;
            return;
        }
        // reset alerts on submit
        this.alertService.clear();
        console.log('11', this.registerForm);
        
        // stop here if form is invalid
        if (this.registerForm.invalid) {
            return;
        }

        this.loading = true;
        this.userService.register(this.registerForm.value)
            .pipe(first())
            .subscribe(
                data => {
                    this.alertService.success('Registration successful', true);
                    this.router.navigate(['/login'], { queryParams: { registered: true }});
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
}