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
     response:string;    
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
    
    change(e)
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
         this.authenticationService.login(this.f.username.value, this.f.password.value, this.selected)
            .pipe(first())
            .subscribe(
                data => {
                  this.response=data;
                  if(this.response=='Doctor login Successfully')
                  {
                    console.log(this.response); 
                    this.router.navigateByUrl('/view-Doctor')
                  }else if(this.response=='Owner login Successfully'){
                    console.log(this.response); 
                    this.router.navigateByUrl('/view-Owner')
                  }else{
                      alert("user not found");
                  }
                   
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    
       
    }
}
