import { Component, OnInit } from '@angular/core';
import { MatrixService } from '../matrix.service';

@Component({
  selector: 'app-matrix',
  templateUrl: './matrix.component.html',
  styleUrls: ['./matrix.component.css']
})
export class MatrixComponent implements OnInit {
  public matrixA = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
  public heightA = 3;
  public widthA = 3;
  public scalarA = 1;

  public matrixB = [[0, 0, 0], [0, 0, 0], [0, 0, 0]];
  public heightB = 3;
  public widthB = 3;
  public scalarB = 1;

  public max = 5;
  public resultMatrix = [[]];
  public resultValue: number;
  public resultBool = false;
  public running = false;

  constructor(private matrixService: MatrixService) { }

  ngOnInit() {
  }
  resize(letter: string) {
    if (!this.running) {
      if (letter === 'A') {
        this.matrixA = [];
        for (let i = 0; i < (this.max - this.heightA + 1); i++) {
          this.matrixA.push([]);
          for (let j = 0; j < this.widthA; j++) {
            this.matrixA[i].push(0);
          }
        }
      } else if (letter === 'B') {
        this.matrixB = [];
        for (let i = 0; i < (this.max - this.heightB + 1); i++) {
          this.matrixB.push([]);
          for (let j = 0; j < this.widthB; j++) {
            this.matrixB[i].push(0);
          }
        }
      }
    }
  }
  add() {
    if (this.running) {
      return;
    }
    if (this.matrixA.length !== this.matrixB.length || this.matrixA[0].length !== this.matrixB[0].length) {
      throw new Error('The matricies must be the same size');
    }
    this.running = true;
    this.resultMatrix = [];
      this.resultBool = false;
    this.matrixService.addMatricies(this.matrixA, this.matrixB).subscribe(result => this.handle(result),
    err => {
      this.running = false;
      throw new Error('Unable to connect to server');
    });
  }
  subtract() {
    if (this.running) {
      return;
    }
    if (this.matrixA.length !== this.matrixB.length || this.matrixA[0].length !== this.matrixB[0].length) {
      throw new Error('The matricies must be the same size');
    }
    this.running = true;
    this.resultMatrix = [];
      this.resultBool = false;
    this.matrixService.subtractMatricies(this.matrixA, this.matrixB).subscribe(result => this.handle(result),
    err => {
      this.running = false;
      throw new Error('Unable to connect to server');
    });
  }
  multiply() {
    if (this.running) {
      return;
    }
    if (this.matrixA[0].length !== this.matrixB.length) {
      throw new Error('Matrix A must have a width equal to the height of Matrix B');
    }
    this.running = true;
    this.resultMatrix = [];
    this.resultBool = false;
    this.matrixService.multiplyMatricies(this.matrixA, this.matrixB).subscribe(result => this.handle(result),
    err => {
      this.running = false;
      throw new Error('Unable to connect to server');
    });
  }
  append() {
    if (this.running) {
      return;
    }
    if (this.matrixA.length !== this.matrixB.length) {
      throw new Error('The matricies must have the same height');
    }
    this.running = true;
    this.resultMatrix = [];
    this.resultBool = false;
    this.matrixService.appendMatricies(this.matrixA, this.matrixB).subscribe(result => this.handle(result),
    err => {
      this.running = false;
      throw new Error('Unable to connect to server');
    });
  }
  transpose(letter: string) {
    if (this.running) {
      return;
    }
    this.running = true;
    this.resultMatrix = [];
    this.resultBool = false;
    if (letter === 'A') {
      this.matrixService.getTranspose(this.matrixA).subscribe(result => this.handleTranspose(result, 'A'),
      err => {
        this.running = false;
        throw new Error('Unable to connect to server');
      });
    } else if (letter === 'B') {
      this.matrixService.getTranspose(this.matrixB).subscribe(result => this.handleTranspose(result, 'B'),
      err => {
        this.running = false;
        throw new Error('Unable to connect to server');
      });
    }
  }
  identity(letter: string) {
    if (this.running) {
      return;
    }
    this.running = true;
    this.resultMatrix = [];
    this.resultBool = false;
    if (letter === 'A') {
      this.matrixService.getIdentityMatrix(this.widthA).subscribe(result => this.handleTranspose(result, 'A'),
      err => {
        this.running = false;
        throw new Error('Unable to connect to server');
      });
    } else if (letter === 'B') {
      this.matrixService.getIdentityMatrix(this.widthB).subscribe(result => this.handleTranspose(result, 'B'),
      err => {
        this.running = false;
        throw new Error('Unable to connect to server');
      });
    }
  }
  inverse(letter: string) {
    if (this.running) {
      return;
    }
    if (letter === 'A') {
      if (this.matrixA.length !== this.matrixA[0].length) {
        throw new Error('The Matrix must be square');
      }
      this.running = true;
      this.resultMatrix = [];
      this.resultBool = false;
      this.matrixService.getInverse(this.matrixA).subscribe(result => this.handleTranspose(result, 'A'),
      err => {
        this.running = false;
        if (err.status === 0) {
          throw new Error('Unable to connect to server');
        } else {
          throw new Error(err.error);
        }
      });
    } else if (letter === 'B') {
      if (this.matrixB.length !== this.matrixB[0].length) {
        throw new Error('The Matrix must be square');
      }
      this.running = true;
      this.resultMatrix = [];
      this.resultBool = false;
      this.matrixService.getInverse(this.matrixB).subscribe(result => this.handleTranspose(result, 'B'),
      err => {
        this.running = false;
        if (err.status === 0) {
          throw new Error('Unable to connect to server');
        } else {
          throw new Error(err.error);
        }
      });
    }
  }
  determinant(letter: string) {
    if (this.running) {
      return;
    }
    if (letter === 'A') {
      if (this.matrixA.length !== this.matrixA[0].length) {
        throw new Error('The Matrix must be square');
      }
      this.running = true;
      this.resultMatrix = [];
      this.resultBool = false;
      this.matrixService.getDeterminant(this.matrixA).subscribe(result => this.handle(result),
      err => {
        this.running = false;
        throw new Error('Unable to connect to server');
      });
    } else if (letter === 'B') {
      if (this.matrixB.length !== this.matrixB[0].length) {
        throw new Error('The Matrix must be square');
      }
      this.running = true;
      this.resultMatrix = [];
      this.resultBool = false;
      this.matrixService.getDeterminant(this.matrixB).subscribe(result => this.handle(result),
      err => {
        this.running = false;
        throw new Error('Unable to connect to server');
      });
    }
  }
  scalarMultiplication(letter: string) {
    if (this.running) {
      return;
    }
    if (letter === 'A') {
      if (typeof this.scalarA !== 'number') {
        throw new Error('The scalar must be a number');
      }
      this.running = true;
      this.resultMatrix = [];
      this.resultBool = false;
      this.matrixService.scalarMultiplyMatrix(this.matrixA, this.scalarA).subscribe(result => this.handleTranspose(result, 'A'),
      err => {
        this.running = false;
        throw new Error('Unable to connect to server');
      });
    } else if (letter === 'B') {
      if (typeof this.scalarB !== 'number') {
        throw new Error('The scalar must be a number');
      }
      this.running = true;
      this.resultMatrix = [];
      this.resultBool = false;
      this.matrixService.scalarMultiplyMatrix(this.matrixB, this.scalarB).subscribe(result => this.handleTranspose(result, 'B'),
      err => {
        this.running = false;
        throw new Error('Unable to connect to server');
      });
    }
  }
  handle(result: number[][] | number) {
    if (typeof result === 'number') {
      this.resultMatrix = [[]];
      this.resultValue = result;
      this.resultBool = true;
    } else {
      this.resultBool = false;
      this.resultMatrix = result;
    }
    this.running = false;
  }
  handleTranspose(result: number[][], letter: string) {
    this.resultMatrix = [[]];
    this.resultValue = null;
    if (letter === 'A') {
      this.matrixA = result;
      this.widthA = this.matrixA[0].length;
      this.heightA = this.max - this.matrixA.length + 1;
    } else if (letter = 'B') {
      this.matrixB = result;
      this.widthB = this.matrixB[0].length;
      this.heightB = this.max - this.matrixB.length + 1;
    }
    this.running = false;
  }
  customTrackBy(index: number, obj: any): any {
    return index;
  }
}
