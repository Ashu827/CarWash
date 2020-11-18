import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  islogin=false;
  constructor(){}
  checkingstatus():Observable<boolean>
  {
      return of(JSON.parse(localStorage.getItem('islogin')));

  }
  logout():void{
      localStorage.removeItem('islogin');
      this.islogin=false;
  }
  valid():Observable<boolean>{
      localStorage.setItem('islogin',JSON.stringify(true));
      this.islogin = true;
      return of(true);
  }
}
