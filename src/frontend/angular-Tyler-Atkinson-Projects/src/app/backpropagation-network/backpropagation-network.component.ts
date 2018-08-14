import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core';
import { BackpropagationService } from '../backpropagation.service';
import { BackpropagationSettings } from '../backpropagation-settings';
import { BackpropagationNetwork } from '../backpropagation-network';
import { Node } from '../node';
import { Line } from '../line';

@Component({
  selector: 'app-backpropagation-network',
  templateUrl: './backpropagation-network.component.html',
  styleUrls: ['./backpropagation-network.component.css']
})

export class BackpropagationNetworkComponent implements OnInit {
 // @ViewChild('networkCanvas') canvas: ElementRef;
 // public context: CanvasRenderingContext2D;
  private trainingOutput = [[0], [0], [0], [0]];
  private trainingInput = [[.9, .1], [.1, .9], [.9, .9], [.1, .1]];
  private runInput = [0, 0];
  private numHidden = 3;
  public inTraining = true;
  private networkOutput: number;
  public settings: BackpropagationSettings = {
    input: this.trainingInput,
    expectedOutput: JSON.parse(JSON.stringify(this.trainingOutput)),
    numHidden: this.numHidden,
    targetError: 0.00001
  };
  private network: BackpropagationNetwork;
  public running = false;

  private inputNodes: Node[];
  private hiddenNodes: Node[];
  private outputNodes: Node[];
  private connections: Line[];

  constructor(private backpropagationService: BackpropagationService) { }


  ngOnInit() {
    this.inputNodes = [{x: 60, y: 60, value: true},
                       {x: 60, y: 140, value: false}];
    this.hiddenNodes = [{x: 200, y: 30, value: true},
                       {x: 200, y: 100, value: true},
                       {x: 200, y: 170, value: true}];
    this.outputNodes = [{x: 350, y: 100, value: false}];
    this.connections = [];
    for (let i = 0; i < this.inputNodes.length; i++) {
      for (let j = 0; j < this.hiddenNodes.length; j++) {
        this.connections.push({x1: this.inputNodes[i].x, y1: this.inputNodes[i].y, x2: this.hiddenNodes[j].x, y2: this.hiddenNodes[j].y});
      }
    }
    for (let i = 0; i < this.hiddenNodes.length; i++) {
      for (let j = 0; j < this.outputNodes.length; j++) {
        this.connections.push({x1: this.hiddenNodes[i].x, y1: this.hiddenNodes[i].y, x2: this.outputNodes[j].x, y2: this.outputNodes[j].y});
      }
    }
  }
  train() {
    if (!this.running) {
      this.running = true;
      for (let i = 0; i < this.settings.expectedOutput.length; i++) {
        for (let j = 0; j < this.settings.expectedOutput[i].length; j++) {
          if (this.trainingOutput[i][j] === 1) {
            this.settings.expectedOutput[i][j] = 0.9;
          } else if (this.trainingOutput[i][j] === 0) {
            this.settings.expectedOutput[i][j] = 0.1;
          } else {
            this.running = false;
            throw new Error('Values must be either 0 or 1');
          }
        }
      }
      console.log(this.settings);
      this.backpropagationService.trainNetwork(this.settings).subscribe(network => this.finishNetwork(network),
      err => {
        this.running = false;
        throw new Error('Unable to connect to server');
      });
    }
  }
  private finishNetwork(network: BackpropagationNetwork) {
    this.network = network;
    this.inTraining = false;
    this.running = false;
  }
  runNetwork() {
    if (!this.running) {
      this.running = true;
      for (let i = 0; i < this.runInput.length; i++) {
        if (this.inputNodes[i].value) {
          this.network.inputNodes[i].data = .9;
        } else {
          this.network.inputNodes[i].data = .1;
        }
      }
      this.backpropagationService.runNetwork(this.network).subscribe(result => this.evaluateResults(result),
      err => {
        this.running = false;
        throw new Error('Unable to connect to server');
      });
    }
  }
  evaluateResults(result: number[]) {
    for (let i = 0; i < result.length; i++) {
      if (result[i] < 0.5) {
        result[i] = 0;
      } else {
        result[i] = 1;
      }
    }
    if (result[0] === 0) {
      this.outputNodes[0].value = false;
    } else {
      this.outputNodes[0].value = true;
    }
    this.running = false;
  }
  changeInput(node: Node) {
    node.value = !node.value;
  }

}
