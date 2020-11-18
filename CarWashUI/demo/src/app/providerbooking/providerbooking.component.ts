import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-providerbooking',
  templateUrl: './providerbooking.component.html',
  styleUrls: ['./providerbooking.component.css']
})
export class ProviderbookingComponent implements OnInit {

  constructor() { }

  providerBooking = JSON.parse(localStorage.getItem('providerbookings'))

  ngOnInit(): void {
  }

}
