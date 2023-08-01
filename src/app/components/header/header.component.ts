import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppCookieService } from 'src/app/services/app-cookie.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  constructor(
    private appCookieService: AppCookieService,
    private router: Router,
  ) { 
  }

  logout() {
    this.appCookieService.deleteAccessToken()
    this.router.navigate(['/login'])
  }

}

