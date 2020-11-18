import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { Router } from '@angular/router';
import { AbstractControl, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-customerlogin',
  templateUrl: './customerlogin.component.html',
  styleUrls: ['./customerlogin.component.css']
})
export class CustomerloginComponent implements OnInit {

  userdetails : any;
  loginForm: FormGroup;
  myStorage = window.localStorage;
  isLogin : boolean = false;
  
  constructor(private route: Router, private apiService: CustomerService) {
    this.loginForm = new FormGroup({
      emailId: new FormControl(null, [Validators.required, Validators.email, Validators.minLength(4)]),
      password: new FormControl(null, Validators.required)
    });
  }

  isValid(controlName) {
    return this.loginForm.get(controlName).invalid && (this.loginForm.get(controlName).touched || this.loginForm.get(controlName).dirty);
  }

  login() {
    console.log(this.loginForm.value);

    if (this.loginForm.valid) {
      this.apiService.login(this.loginForm.value)
        .subscribe(
          (data) => {
            if (data) {
              this.userdetails=data;
              this.isLogin=true;
              localStorage.setItem('isUserLoggedIn', JSON.stringify(this.isLogin));
              console.log(" customer login completed")
              localStorage.setItem('username',this.userdetails.name)
              localStorage.setItem('userdetails',JSON.stringify(this.loginForm.value))
              console.log(this.userdetails.name + " username")
              alert('Successfully logged In')
              this.route.navigateByUrl('/')
            }

            else {
              alert("Invalid emailId or password")
            }
          },
          error => {
            console.log(error + 'error')
          }
        );
    }


  }

  ngOnInit(): void {

  }

}
