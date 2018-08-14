import { Injectable } from '@angular/core';
import { City } from './city';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WebService } from './web.service';

@Injectable({
  providedIn: 'root'
})

export class SalesmanService {
  private url = this.web.baseSite + '/salesman';
  constructor(private http: HttpClient, private web: WebService) { }

  runGeneticSalesman(cities: number | City[]): Observable<City[]> {
    console.log('running genetic');
    if (typeof cities === 'number') {
      return this.http.get<City[]>(`${this.url}/genetic/${cities}`);
    }
    return this.http.post<City[]>(`${this.url}/genetic`, cities);
  }
  runAnnealingSalesman(cities: number | City[]): Observable<City[]> {
    if (typeof cities === 'number') {
      return this.http.get<City[]>(`${this.url}/annealing/${cities}`);
    }
    return this.http.post<City[]>(`${this.url}/annealing`, cities);
  }
}
