import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student.service';
import {FormControl,FormBuilder,FormGroup,Validators} from '@angular/forms';
import { Student } from '../student';
import { registeruser } from '../register-user';
import { data, event } from 'jquery';
import { error } from 'util';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../_services/authentication.service';
import { AlertService } from '../_services/alert.service';
import { first } from 'rxjs/operators';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class AddStudentComponent implements OnInit {

  loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    selected:string;    
    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService
    ) {
        // redirect to home if already logged in
        if (this.authenticationService.currentUserValue) { 
            this.router.navigate(['/']);
        }
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || 'view-pet';
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }
    changeLoger(e)
    {
       this.selected=e.target.value;

    }
    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }

        this.loading = true;
        this.authenticationService.login(this.f.username.value, this.f.password.value)
            .pipe(first())
            .subscribe(
                data => {
                    //this.router.navigate([this.returnUrl]);
                    this.router.navigateByUrl('/view-Pet')

                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
}
