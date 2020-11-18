import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../customer.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  name = (localStorage.getItem('username'))
  usercardetails = JSON.parse(localStorage.getItem('BookingCarDetails'))
  userDetails = JSON.parse(localStorage.getItem('userdetails'))
  isUserLogin=JSON.parse(localStorage.getItem('isUserLoggedIn'));
  isProviderLogin=JSON.parse(localStorage.getItem('isProviderLoggedIn'));
  providerBooking = JSON.parse(localStorage.getItem('providerbookings'))

  constructor(private route: Router, private apiService: CustomerService) { }

  ngOnInit(): void {
    
  }
  home(){
    if(this.isProviderLogin==true){
      this.route.navigateByUrl('/provider-home')
    }
    else{
      this.route.navigateByUrl('/')
    }
  }
  customeraccount(){
    if(this.isUserLogin==true){
    this.route.navigateByUrl('/booking-history')
    }
    else{
      alert('please login to your account')
    }
  }

  provideraccount(){
    if(this.isProviderLogin==true){
      this.route.navigateByUrl('/provider-history')
    }
    else{
      alert('Please login to your account')
    }
  }

  logout() {
    window.localStorage.removeItem('username')
    window.localStorage.removeItem('BookingCarDetails')
    window.localStorage.removeItem('userdetails')
    window.localStorage.removeItem('isUserLoggedIn')
    window.localStorage.removeItem('isProviderLoggedIn')
    window.localStorage.removeItem('providerbookings')
  }

}
