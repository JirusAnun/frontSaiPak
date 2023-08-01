import { Injectable, inject } from '@angular/core';
import { AppCookieService } from './app-cookie.service';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';

export @Injectable({
  providedIn: 'root'
})

class AuthGuardService {

  canActivate(appCookieService: AppCookieService, router: Router){
    if( appCookieService.hasAccessToken() ) {
      return true;
    }

    router.navigate(['/login'])
    
    return false;
  }

}

export const canActivateTeam: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  return inject(AuthGuardService).canActivate((inject(AppCookieService)), (inject(Router)));
};