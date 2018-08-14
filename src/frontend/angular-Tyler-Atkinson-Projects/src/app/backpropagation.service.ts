import { Injectable } from '@angular/core';
import { City } from './city';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BackpropagationSettings } from './backpropagation-settings';
import { BackpropagationNetwork } from './backpropagation-network';
import { WebService } from './web.service';

@Injectable({
  providedIn: 'root'
})
export class BackpropagationService {
  private url = this.web.baseSite + '/backpropagation';
  constructor(private http: HttpClient, private web: WebService) { }

  trainNetwork(settings: BackpropagationSettings): Observable<BackpropagationNetwork> {
    return this.http.post<BackpropagationNetwork>(`${this.url}`, settings);
  }
  runNetwork(network: BackpropagationNetwork): Observable<number[]> {
    return this.http.post<number[]>(`${this.url}/run`, network);
  }
}
