import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';

import { StudentListComponent } from './student-list/student-list.component';
import { AddStudentComponent } from './login/login.component';
import { DoctorListComponent } from './doctor-list/doctor-list.component';
import { OwnerListComponent } from './owner-list/owner-list.component';
import { DescriptionListComponent } from './description-list/description-list.component';
import { ViewDiscriptionComponent } from './view-discription/view-discription.component';
import { ViewPetComponent } from './view-pet/view-pet.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'view-pet', component: StudentListComponent },
  { path: 'login', component: AddStudentComponent },
  { path: 'register-doctor', component: RegisterComponent },
  { path: 'view-Doctor', component: DoctorListComponent},
  { path:  'view-Owner', component:OwnerListComponent},

  { path: 'view-Description', component:DescriptionListComponent},
  { path: 'only-view-Description', component:ViewDiscriptionComponent},
  { path: 'only-view-Pet', component:ViewPetComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
