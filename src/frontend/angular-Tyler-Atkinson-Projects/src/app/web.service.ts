import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WebService {
  public baseSite = 'https://tyler-atkinson-api.herokuapp.com/';
  // public baseSite = 'http://localhost:8080';
  constructor() { }
}
