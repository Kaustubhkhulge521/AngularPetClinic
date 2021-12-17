import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable, Subject } from 'rxjs';
import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-view-discription',
  templateUrl: './view-discription.component.html',
  styleUrls: ['./view-discription.component.css']
})
export class ViewDiscriptionComponent implements OnInit {

  constructor(private studentservice:StudentService) { }

  studentsArray: any[] = [];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any>= new Subject();


  students: Observable<Student[]>;
  student : Student=new Student();
  deleteMessage=false;
  descriptionlist:any;
  isupdated = false;    
 
  title = 'petClinicApp';
  descriptionToUpdate = {
    petId:"",
    petName: "",
    speciesId: "",
    petBreed: "",
    petDisease: "",
    medicine: "",
    medicineDose: ""
  };
  ngOnInit() {
    this.isupdated=false;
    this.dtOptions = {
      pageLength: 6,
      stateSave:true,
      lengthMenu:[[6, 16, 20, -1], [6, 16, 20, "All"]],
      processing: true
    };   
    this.studentservice.getDescriptionList().subscribe(data =>{
    this.students =data;
    console.log("data"+JSON.stringify(data));
    this.dtTrigger.next();
    })
  }
  
}
