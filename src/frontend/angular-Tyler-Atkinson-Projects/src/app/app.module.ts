import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

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
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    MatProgressSpinnerModule,
    AppRoutingModule,
  ],
  providers: [{provide: ErrorHandler, useClass: AppComponent}],
  bootstrap: [AppComponent]
})
export class AppModule { }
