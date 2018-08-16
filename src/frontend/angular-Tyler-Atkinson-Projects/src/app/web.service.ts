import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WebService {
  public baseSite = 'https://tyler-atkinson-api.herokuapp.com/';
  // public baseSite = 'http://localhost:8080';
  constructor(private http: HttpClient) { }
  checkStatus() {
    return this.http.get(this.baseSite + '/status');
  }
}
