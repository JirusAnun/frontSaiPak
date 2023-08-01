import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppCookieService } from 'src/app/services/app-cookie.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{
  
  loginFormGroup: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  })
  
  constructor(
    private userService: UserService,
    private appCookieService: AppCookieService,
    private router: Router
  ) {

  }
  ngOnInit(): void {
    this.appCookieService.deleteAccessToken();
  }

  onSubmit(): void {
    
    if(this.loginFormGroup.invalid) return;

    let username = this.loginFormGroup.value.username;
    let password = this.loginFormGroup.value.password;

    this.userService.login(username, password).subscribe({
      next: (response) => {
        this.appCookieService.setAccessToken(response.token);
        this.router.navigate([''])
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

}
