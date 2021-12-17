import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable, Subject } from 'rxjs';
import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-owner-list',
  templateUrl: './owner-list.component.html',
  styleUrls: ['./owner-list.component.css']
})
export class OwnerListComponent implements OnInit {

  constructor(private studentservice:StudentService) { }

  studentsArray: any[] = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any>= new Subject();


  students: Observable<Student[]>;
  student : Student=new Student();
  deleteMessage=false;
  ownerlist:any;
  isupdated = false;    
 
  title = 'petClinicApp';
  ownerToUpdate = {
    id:"",
    firstName: "",
    lastname: "",
    email: "",
    mobileNumber: "",
    password:""
  };
  ngOnInit() {
    this.isupdated=false;
    this.dtOptions = {
      pageLength: 6,
      stateSave:true,
      lengthMenu:[[6, 16, 20, -1], [6, 16, 20, "All"]],
      processing: true
    };   
    this.studentservice.getOwnerList().subscribe(data =>{
    this.students =data;
    console.log("data"+JSON.stringify(data));
    this.dtTrigger.next();
    })
  }
  
  deleteOwner(id: number) {
    this.studentservice.deleteOwner(id)
      .subscribe(
        data => {
          console.log(data);
          this.deleteMessage=true;
          this.studentservice.getOwnerList().subscribe(data =>{
            this.students =data
            })
        },
        error => console.log(error));
  }

  
  insertOwner(student) {
    this.studentservice.insertOwner(student)
      .subscribe(
        data => {
          console.log(data);
          this.deleteMessage=true;
          this.studentservice.getDoctorList().subscribe(data =>{
            this.students =data
            })
        },
        error => console.log(error));
  }

  updateOwner(id: number){
    this.studentservice.getOwner(id)
      .subscribe(
        data => {
          this.ownerlist=data 
          console.log("hi"+this.ownerlist);          
        },
        error => console.log(error));
  }

  studentupdateform=new FormGroup({
    petId:new FormControl(),
    petName:new FormControl(),
    ownerName:new FormControl(),
   // petSpecies:new FormControl()
  });

  updateStu(updstu){
    this.student=new Student(); 
   this.student.petId=this.petId.value;
   this.student.petName=this.petName.value;
   this.student.ownerName=this.ownerName.value;
   //this.student.student_branch=this.StudentBranch.value;
   //console.log(this.StudentBranch.value);
   

   this.studentservice.updateOwner(this.student.petId,this.student).subscribe(
    data => {   
      console.log("owners::"+this.student.petId);  
      this.isupdated=true;
      this.studentservice.getOwnerList().subscribe(data =>{
        this.students =data
        })
    },
    error => console.log(error));
  }
  get petId(){
    return this.studentupdateform.get('petId');
  }
  get petName(){
    return this.studentupdateform.get('petName');
  }
  get ownerName(){
    return this.studentupdateform.get('ownerName');
  }
  get StudentName(){
    return this.studentupdateform.get('student_name');
  }

  get StudentEmail(){
    return this.studentupdateform.get('student_email');
  }

  get StudentBranch(){
    return this.studentupdateform.get('student_branch');
  }

  get StudentId(){
    return this.studentupdateform.get('student_id');
  }

  changeisUpdate(){
    this.isupdated=false;
  }
  edit(student){
    this.ownerToUpdate= student;
  }
  
  updateOwner2(){
    this.studentservice.updateOwner2(this.ownerToUpdate).subscribe(
      (resp) =>{
        console.log(resp);
      },
      (err) =>{
        console.log(err);
      }
    );
  }

}
