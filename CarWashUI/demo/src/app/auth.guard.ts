import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
    providedIn: 'root'
  })
export class AuthGuard implements CanActivate {
  
    constructor(private authservice:AuthService, private route:Router){
  
    }
    
    canActivate(
      route: ActivatedRouteSnapshot,
      state: RouterStateSnapshot):  boolean {
      return this.checkAuth();
    }
    checkAuth(): boolean{
      if(this.authservice.islogin){ return true;}
      return false; 
       
    }
  }
  