import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Router } from '@angular/router';
import { CarDetails } from '../carbooking/CarDetails';

@Component({
  selector: 'app-bookinghistory',
  templateUrl: './bookinghistory.component.html',
  styleUrls: ['./bookinghistory.component.css']
})
export class BookinghistoryComponent implements OnInit {

  constructor(private apiService: CustomerService, private route: Router) { }

  datalist: any
  credentials = JSON.parse(localStorage.getItem('userdetails'))

  onclick() {
    this.apiService.getCarDetail(this.credentials).subscribe(
      (data) => {
        if(data) {
          this.datalist = data;
          console.log("car detils from database" + this.datalist[0].name)
        }
        else {
          console.log("error")
          alert("You don't have any booking history")
        }
      }
    )
  }
  ngOnInit(): void {
    this.onclick();


  }

}
