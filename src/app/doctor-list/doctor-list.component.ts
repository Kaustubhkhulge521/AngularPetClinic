import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable, Subject } from 'rxjs';
import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit {

  constructor(private studentservice:StudentService) { }

  studentsArray: any[] = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any>= new Subject();


  students: Observable<Student[]>;
  student : Student=new Student();
  deleteMessage=false;
  studentlist:any;
  isupdated = false;    
 
  studentToUpdate ={

    id:"",
    Name:"",
    designation:"",
    address:"",
    mobileNumber:"",
    qualification:"",
    username:"",
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
    this.studentservice.getDoctorList().subscribe(data =>{
    this.students =data;
    console.log("data"+JSON.stringify(data));
    this.dtTrigger.next();
    })
  }
  
  deleteDoctor(id: number) {
    this.studentservice.deleteDoctor(id)
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

insertDoctor(student) {
    this.studentservice.insertDoctor(student)
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

  

  updateDoctor(id: number){
    this.studentservice.getDoctor(id)
      .subscribe(
        data => {
          this.studentlist=data 
          console.log("hi"+this.studentlist);          
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
   

   this.studentservice.updateStudent(this.student.petId,this.student).subscribe(
    data => {   
      console.log("pet::"+this.student.petId);  
      this.isupdated=true;
      this.studentservice.getStudentList().subscribe(data =>{
        this.students =data
        })
    },
    error => console.log(error));
  }

  edit(student){
    
       this.studentToUpdate = student;
  }

  updateDoctor1(){
    this.studentservice.updateDoctor1(this.studentToUpdate).subscribe(
      (resp) =>{
        console.log(resp);
      },
      (err) =>{
        console.log(err);
      }
    );
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


}
