import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {DataTablesModule} from 'angular-datatables';
import { StudentListComponent } from './student-list/student-list.component';
import { AddStudentComponent } from './login/login.component';
import { DoctorListComponent } from './doctor-list/doctor-list.component';
import { OwnerListComponent } from './owner-list/owner-list.component';
import { DescriptionListComponent } from './description-list/description-list.component';
import { ViewPetComponent } from './view-pet/view-pet.component';
import { ViewDiscriptionComponent } from './view-discription/view-discription.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    StudentListComponent,
    AddStudentComponent,
    DoctorListComponent,
    OwnerListComponent,
    DescriptionListComponent,
    ViewPetComponent,
    ViewDiscriptionComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    DataTablesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
  
})
export class AppModule { }
