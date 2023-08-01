import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ILoginResponse } from '../interfaces/i-login-response';
import { IRegisterResponse } from '../interfaces/i-register-response';
import { IScoreResponse } from '../interfaces/i-score-respons';
import { IHintResponse } from '../interfaces/i-hint-response';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  login(username: string, password: string): Observable<ILoginResponse> {
    let url = 'http://localhost:8080/user/login';
    let body = {
      username: username,
      password: password
    }
    return this.http.post<ILoginResponse>(url, body);
  }

  register(username: string, password: string, confirmpassword: string): Observable<IRegisterResponse> {
    let url = 'http://localhost:8080/user/register';
    let body = {
      username: username,
      password: password,
      confirmpassword: confirmpassword
    }
    return this.http.post<IRegisterResponse>(url, body);
  }

  getScore(): Observable<IScoreResponse> {
    let url = 'http://localhost:8080/user/getscore';
    return this.http.get<IScoreResponse>(url);
  }

  setScore(score: number): Observable<IScoreResponse> {
    let url = 'http://localhost:8080/user/postscore';
    let body = {
      score: score
    }
    return this.http.post<IScoreResponse>(url, body);
  }

  getHint(): Observable<IHintResponse>{ 
    let url = 'http://localhost:8080/user/showhint';
    return this.http.get<IHintResponse>(url);
  }

  buyHint(score: number): Observable<any>{
    let url = 'http://localhost:8080/user/buyhint';
    let body = {
      score: score
    }
    return this.http.post(url, body);
  }

}
