import { Component, OnInit } from '@angular/core';
import {FormControl,FormBuilder,FormGroup,Validators} from '@angular/forms';
import { from } from 'rxjs';

import { RegisterUserService } from '../register-user.service';
import { registeruser } from '../register-user';
import { registerDoctor } from '../register-doctor';
import { first } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  returnUrl: string;
  constructor(private registerUserService:RegisterUserService,
    private route: ActivatedRoute,
    private router: Router,private fb: FormBuilder
  ) { 
  
     
  }

  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || 'login';
   
  }
  show=true;
  showForOwner=false;
  Registeruser : registeruser=new registeruser();
  RegisterDoctor:registerDoctor=new registerDoctor();
  submitted = false;

 
    form=new FormGroup({
    firstName:new FormControl('',Validators.required),
    lastName:new FormControl('',Validators.required),
    email:new FormControl('',[Validators.required,Validators.email]),
    mobileNumber:new FormControl('',Validators.required),
    username:new FormControl('',Validators.required),
    password:new FormControl('',Validators.required),
    name:new FormControl('',Validators.required),
    designation:new FormControl('',Validators.required),
    address:new FormControl('',Validators.required),
    qualification:new FormControl('',Validators.required)

  });
  
  
  change(e) {
    console.log(e.target.value);
    if(e.target.value=="Doctor")
    {
     this.show=true;
     this.showForOwner=false;
    }else if(e.target.value=="Owner")
    {
      this.show=false;
      this.showForOwner=true;
    }
  }
  registerDoctor(registerDoctorform)
  {
    console.log("this.email.value");
    this.RegisterDoctor=new registerDoctor();
    this.RegisterDoctor.name=this.name.value;
    this.RegisterDoctor.designation=this.designation.value;
    this.RegisterDoctor.address=this.address.value;
    this.RegisterDoctor.qualification=this.qualification.value;
    this.RegisterDoctor.mobileNumber=this.mobileNumber.value;
    this.RegisterDoctor.username=this.username.value;
    this.RegisterDoctor.password=this.password.value;
    
    this.submitted=true;
    this.saveDoctor();
  }
  saveDoctor() {
    this.registerUserService.createDoctor(this.RegisterDoctor)
     .pipe(first())
    .subscribe(
        data => {
            this.router.navigate([this.returnUrl]);
        },
        error => {
          //  this.alertService.error(error);
          //  this.loading = false;
        });
  }
  register(registerform){
    // console.log(this.partnerName.value);
    // console.log(this.partnerEmail.value);
    console.log(this.email.value);
    this.Registeruser=new registeruser();
    this.Registeruser.firstName=this.firstName.value;
    this.Registeruser.lastName=this.lastName.value;
    this.Registeruser.email=this.email.value;
    this.Registeruser.mobileNumber=this.mobileNumber.value;
    this.Registeruser.username=this.username.value;
    this.Registeruser.password=this.password.value;
    this.submitted=true;
    this.save();
  }

  save() {
    this.registerUserService.createPartner(this.Registeruser)
    .pipe(first())
    .subscribe(
        data => {
            this.router.navigate([this.returnUrl]);
        },
        error => {
          //  this.alertService.error(error);
          //  this.loading = false;
        });
    
  //  .subscribe(data => console.log(data), error => console.log(error));
// ..   this.Registeruser = new registeruser();
  }
  
  get registerFormControl() {
    return this.form.controls;
  }


  get firstName(){
    return this.form.get('firstName');
  }
  get name(){
    return this.form.get('name');
  }
get designation()
{
  return this.form.get('designation');
}
get address()
{
  return this.form.get('address');
}
get qualification()
{
  return this.form.get('qualification');
}
  get email(){
    return this.form.get('lastName');
  }
  get lastName(){
    return this.form.get('lastName');
  }
  get mobileNumber(){
    return this.form.get('mobileNumber');
  }
  get username(){
    return this.form.get('username');
  }
  get password(){
    return this.form.get('password');
  }
}