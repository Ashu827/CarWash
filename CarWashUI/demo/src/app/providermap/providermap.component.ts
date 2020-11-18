import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-providermap',
  templateUrl: './providermap.component.html',
  styleUrls: ['./providermap.component.css']
})
export class ProvidermapComponent implements OnInit {

  constructor(private route: Router, ) { }

  finish(){
    this.route.navigateByUrl('/review')
  }
  ngOnInit(): void {
  }

}
