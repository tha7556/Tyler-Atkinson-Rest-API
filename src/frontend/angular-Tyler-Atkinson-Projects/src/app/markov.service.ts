import { Injectable } from '@angular/core';
import { Tweet } from './tweet';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { WebService } from './web.service';
@Injectable({
  providedIn: 'root'
})
export class MarkovService {
  private url = this.web.baseSite + '/markov';
  private user: string;
  constructor(private http: HttpClient, private web: WebService) { }

  getTweets(user: string, quantity: number): Observable<Tweet[]> {
    return this.http.get<Tweet[]>(`${this.url}/${user}/${quantity}`);
  }
  setUser(user: string) {
    this.user = user;
  }
  getUser(): string {
    return this.user;
  }
}
