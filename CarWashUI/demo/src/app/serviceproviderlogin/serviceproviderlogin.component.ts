import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AbstractControl, FormGroup, FormControl, Validators } from '@angular/forms';
import { ServiceproviderService } from '../serviceprovider.service';
@Component({
  selector: 'app-serviceproviderlogin',
  templateUrl: './serviceproviderlogin.component.html',
  styleUrls: ['./serviceproviderlogin.component.css']
})
export class ServiceproviderloginComponent implements OnInit {

  loginForm: FormGroup;
  userdetails: any;
  isLogin : boolean = false;

  constructor(private route: Router, private providerService: ServiceproviderService) {
    this.loginForm = new FormGroup({
      emailId: new FormControl(null, [Validators.required, Validators.email, Validators.minLength(4)]),
      password: new FormControl(null, Validators.required)
    });
  }

  isValid(controlName) {
    return this.loginForm.get(controlName).invalid && (this.loginForm.get(controlName).touched || this.loginForm.get(controlName).dirty);
  }

  login() {
    if (this.loginForm.valid) {
      this.providerService.login(this.loginForm.value)
        .subscribe(
          (data) => {
            if (data) {
              this.userdetails = data;
              this.isLogin=true;
              localStorage.setItem('isProviderLoggedIn', JSON.stringify(this.isLogin));
              alert('Successfully logged In')
              localStorage.setItem('username', this.userdetails.name)
              this.route.navigateByUrl('/provider-home')
            }

            else {
              alert("Invalid credentials for service provider")
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
