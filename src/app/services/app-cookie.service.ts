import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AppCookieService {

  constructor(private cookieService: CookieService) { }

  setAccessToken(token: string): void {
    this.cookieService.set('ACCESS_TOKEN', token);
  }

  setScore(score: number): void {
    let string_score = <unknown>score
    this.cookieService.set('SCORE', <string>string_score);
  }

  getAccessToken(): string{
    return this.cookieService.get('ACCESS_TOKEN');
  }

  getScore(): number{
    let score = this.cookieService.get('SCORE');
    let return_score = <unknown>score
    return <number>return_score;
  }

  deleteAccessToken(): void{
    return this.cookieService.delete('ACCESS_TOKEN');
  }

  deletePoint(): void{
    return this.cookieService.delete('POINT');
  }

  hasAccessToken(): boolean{
    return this.cookieService.check('ACCESS_TOKEN');
  }
}
