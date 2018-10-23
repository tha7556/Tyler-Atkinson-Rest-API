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
import { ContactComponent } from './contact/contact.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ExhaustiveTspComponent } from './exhaustive-tsp/exhaustive-tsp.component';
import { MazeSolverComponent } from './maze-solver/maze-solver.component';
import { SortingComponent } from './sorting/sorting.component';
import { WebsiteComponent } from './website/website.component';

const appRoutes: Routes = [
  {path: 'contact', component: ContactComponent},
  {path: 'projects/backpropagation', component: BackpropagationNetworkComponent},
  {path: 'projects/markov', component: MarkovComponent},
  {path: 'projects/matrix', component: MatrixComponent},
  {path: 'projects/salesman', component: TSPComponent},
  {path: 'projects/exhausted_salesman', component: ExhaustiveTspComponent},
  {path: 'projects/maze', component: MazeSolverComponent},
  {path: 'projects/portfolio', component: WebsiteComponent},
  {path: 'projects', component: ProjectListComponent},
  {path: 'about', component: AboutComponent},
  {path: '', component: WelcomeComponent},
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
    SortingComponent,
    WebsiteComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatProgressSpinnerModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [{provide: ErrorHandler, useClass: AppComponent}],
  bootstrap: [AppComponent]
})
export class AppModule { }
