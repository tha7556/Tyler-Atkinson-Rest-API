import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectListComponent } from './project-list/project-list.component';
import { AboutComponent } from './about/about.component';

const routes: Routes = [
  { path: 'projects', component: ProjectListComponent} ,
  { path: 'about', component: AboutComponent }
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
