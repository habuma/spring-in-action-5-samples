import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'big-button',
  templateUrl: 'bigbutton.component.html',
  styleUrls: ['./bigbutton.component.css']
})

export class BigButtonComponent implements OnInit {

  @Input() label: String;

  constructor() { }

  ngOnInit() { }
}
