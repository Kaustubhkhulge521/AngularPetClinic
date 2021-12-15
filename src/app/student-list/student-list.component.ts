import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student.service';
import { Student } from '../student';
import { Observable,Subject } from "rxjs";

import {FormControl,FormGroup,Validators} from '@angular/forms';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

 constructor(private studentservice:StudentService) { }
  studentsArray: any[] = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any>= new Subject();
  students: Observable<Student[]>;
  student : Student=new Student();
  deleteMessage=false;
  studentlist:any;
  isupdated = false;    
 updatedList:Student=new Student();

  ngOnInit() {
    this.isupdated=false;
    this.dtOptions = {
      pageLength: 6,
      stateSave:true,
      lengthMenu:[[6, 16, 20, -1], [6, 16, 20, "All"]],
      processing: true
    };   
    this.studentservice.getStudentList().subscribe(data =>{
    this.students =data;
    console.log("data"+JSON.stringify(data));
    this.dtTrigger.next();
    })
  }
  
  deleteStudent(id: number) {
    this.studentservice.deleteStudent(id)
      .subscribe(
        data => {
          console.log(data);
          this.deleteMessage=true;
          this.studentservice.getStudentList().subscribe(data =>{
            this.students =data
            })
        },
        error => console.log(error));
  }


  updateStudent(id: number){
    console.log(id);
    this.studentservice.getStudent(id)
      .subscribe(
        data => {
          this.studentlist=data 
          this.updatedList=this.studentlist; 
          console.log("hi"+JSON.stringify(this.updatedList.petId));  
               
        },
        error => console.log(error));
  }

  studentupdateform=new FormGroup({
    petId:new FormControl(),
    petName:new FormControl(),
    ownerName:new FormControl(),
    petSpecies:new FormControl(),
    petSymptoms:new FormControl()
  });

  updateStu(updstu){
    this.student=new Student(); 
   this.student.petId=this.petId.value;
   this.student.petName=this.petName.value;
   this.student.ownerName=this.ownerName.value;
  this.student.petSpecies=this.petSpecies.value;
  this.student.petSymptoms=this.petSymptoms.value;
   this.studentservice.updateStudent(this.student.petId,this.student).subscribe(
    data => {   
       this.studentservice.getStudentList().subscribe(data =>{
        this.students =data
        })
    },
    error => console.log(error));
  }
  get petId(){
    return this.studentupdateform.get('petId');
  }
  get petSpecies(){
    return this.studentupdateform.get('petSpecies');
  }
  get petSymptoms(){
    return this.studentupdateform.get('petSymptoms');
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
}
