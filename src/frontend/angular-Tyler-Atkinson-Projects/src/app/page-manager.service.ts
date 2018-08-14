import { Injectable, ElementRef } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PageManagerService {
  public index: number;
  private navElements: ElementRef[];
  constructor() { }
  setElements(elements: ElementRef[]) {
    this.navElements = elements;
  }
  setIndex(index: number) {
    this.index = index;
    this.updateNavBar();
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
  }
  updateNavBar() {
    for (let i = 0; i < this.navElements.length; i++) {
      if (this.index === i) {
        this.navElements[i].nativeElement.className = 'active';
      } else {
        this.navElements[i].nativeElement.className = 'inactive';
      }
    }
  }
}
