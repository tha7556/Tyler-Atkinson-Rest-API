import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { MarkovComponent } from './markov/markov.component';
import { TweetComponent } from './tweet/tweet.component';
import { BackpropagationNetworkComponent } from './backpropagation-network/backpropagation-network.component';
import { MatrixComponent } from './matrix/matrix.component';
import { TSPComponent } from './tsp/tsp.component';
import { AboutComponent } from './about/about.component';
import { ProjectListComponent } from './project-list/project-list.component';
import { AppRoutingModule } from './/app-routing.module';
import { ContactComponent } from './contact/contact.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ExhaustiveTspComponent } from './exhaustive-tsp/exhaustive-tsp.component';
import { MazeSolverComponent } from './maze-solver/maze-solver.component';
import { WorkoutComponent } from './workout/workout.component';
import { SortingComponent } from './sorting/sorting.component';
import { PcHawkComponent } from './pc-hawk/pc-hawk.component';
import { WebsiteComponent } from './website/website.component';

const appRoutes: Routes = [
  {path: '**', component: AppComponent }];

@NgModule({
  declarations: [
    AppComponent,
    MarkovComponent,
    TweetComponent,
    BackpropagationNetworkComponent,
    MatrixComponent,
    TSPComponent,
    AboutComponent,
    ProjectListComponent,
    ContactComponent,
    WelcomeComponent,
    ExhaustiveTspComponent,
    MazeSolverComponent,
    WorkoutComponent,
    SortingComponent,
    PcHawkComponent,
    WebsiteComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatProgressSpinnerModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [{provide: ErrorHandler, useClass: AppComponent}],
  bootstrap: [AppComponent]
})
export class AppModule { }
