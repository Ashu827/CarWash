import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceproviderService } from '../serviceprovider.service';


@Component({
  selector: 'app-serviceproviderhome',
  templateUrl: './serviceproviderhome.component.html',
  styleUrls: ['./serviceproviderhome.component.css']
})
export class ServiceproviderhomeComponent implements OnInit {

  detailsList : any;
  name = (localStorage.getItem('username'))

  constructor(private route : Router, private providerService : ServiceproviderService) { 
   
  }
  

  carBookingDetails(){
    this.providerService.getcardetails()
    .subscribe(details => {
      console.log(details)
      if(details){
        this.detailsList = details;
        console.log("data from customer " + this.detailsList);
      }
      else{
        alert('please Re-login to your account to get all booking detils')
      }
    })
  }
  submit(details:any){
    alert("you will get this booking details on your mail")
    console.log("booking details name"+ details.name);
    localStorage.setItem('providerbookings', JSON.stringify(details));
    this.route.navigateByUrl('/map')
  }

  ngOnInit(): void {
    this.carBookingDetails();
  }

}
