import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppCookieService } from './app-cookie.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor{

  constructor(
    private appCookieService: AppCookieService
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let token = this.appCookieService.getAccessToken()
    

    if(token) {
      //modify token
      let modified = req.clone(
        {
          headers: req.headers.set('Authorization', `Bearer ${token}`)
        }
      );

      return next.handle(modified);
    }

    return next.handle(req);

  }
}
