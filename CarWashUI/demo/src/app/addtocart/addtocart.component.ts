import { Component, OnInit } from '@angular/core';
import { NgbSlideEvent, NgbSlideEventSource } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-addtocart',
  templateUrl: './addtocart.component.html',
  styleUrls: ['./addtocart.component.css']
})
export class AddtocartComponent implements OnInit { 
  

    constructor() { }
    
    islogin=JSON.parse(localStorage.getItem('isLoggedIn'));

    ngOnInit() {  
      console.log(this.islogin +"islogin")
      
    }  

    

}
