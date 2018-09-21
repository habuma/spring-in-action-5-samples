import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'group-box',
  templateUrl: 'groupbox.component.html',
  styleUrls: ['./groupbox.component.css']
})

export class GroupBoxComponent implements OnInit {
  @Input() title: String;

  constructor() { }

  ngOnInit() { }
}
