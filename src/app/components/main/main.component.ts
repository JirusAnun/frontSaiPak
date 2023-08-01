import { Component, OnDestroy, OnInit } from '@angular/core';
import { AppCookieService } from 'src/app/services/app-cookie.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit{
  score = 0;
  clickCounter = 0;
  constructor (
    private userService: UserService,
    private appCookieService: AppCookieService
  ) { }

  ngOnInit(): void {
    this.userService.getScore().subscribe({
      next: (response) => {
        this.score = response.score;
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

  clickCount(): void{
    this.clickCounter++;
    this.score++;
    if(this.clickCounter==20){
      this.userService.setScore(this.score).subscribe({
        error: (error) => {
          console.log(error)
        },
        complete: () => {
          this.clickCounter=0;
          this.appCookieService.setScore(this.score);
        }
      })
    }
  }
  
  

} 
