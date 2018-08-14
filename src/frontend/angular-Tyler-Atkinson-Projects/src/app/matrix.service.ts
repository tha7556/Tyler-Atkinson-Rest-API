import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WebService } from './web.service';

@Injectable({
  providedIn: 'root'
})
export class MatrixService {
  private url = this.web.baseSite + '/matrix';
  constructor(private http: HttpClient, private web: WebService) { }

  getIdentityMatrix(width: number): Observable<number[][]> {
    return this.http.get<number[][]>(`${this.url}/identity/${width}`);
  }
  addMatricies(matrixA: number[][], matrixB: number[][]): Observable<number[][]> {
    const matricies = [matrixA, matrixB];
    return this.http.post<number[][]>(`${this.url}/add`, matricies);
  }
  subtractMatricies(matrixA: number[][], matrixB: number[][]): Observable<number[][]> {
    const matricies = [matrixA, matrixB];
    return this.http.post<number[][]>(`${this.url}/subtract`, matricies);
  }
  multiplyMatricies(matrixA: number[][], matrixB: number[][]): Observable<number[][]> {
    const matricies = [matrixA, matrixB];
    return this.http.post<number[][]>(`${this.url}/multiply`, matricies);
  }
  appendMatricies(matrixA: number[][], matrixB: number[][]): Observable<number[][]> {
    const matricies = [matrixA, matrixB];
    return this.http.post<number[][]>(`${this.url}/append`, matricies);
  }
  scalarMultiplyMatrix(matrix: number[][], scalar: number): Observable<number[][]> {
    return this.http.post<number[][]>(`${this.url}/multiplyScalar/${scalar}`, matrix);
  }
  getTranspose(matrix: number[][]): Observable<number[][]> {
    return this.http.post<number[][]>(`${this.url}/transpose`, matrix);
  }
  getDeterminant(matrix: number[][]): Observable<number> {
    return this.http.post<number>(`${this.url}/determinant`, matrix);
  }
  getInverse(matrix: number[][]): Observable<number[][]> {
    return this.http.post<number[][]>(`${this.url}/inverse`, matrix);
  }
}
