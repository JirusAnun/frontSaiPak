import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppCookieService } from 'src/app/services/app-cookie.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss']
})
export class ShopComponent implements OnInit{

  hint1: String = '';
  hint2: String = '';
  hint3: String = '';
  hint4: String = '';
  hint5: String = '';
  hint1Status: boolean = false;
  hint2Status: boolean = false;
  hint3Status: boolean = false;
  hint4Status: boolean = false;
  hint5Status: boolean = false;
  hintPrice: number = 1000;
  score: number = 0;
  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userService.getScore().subscribe({
      next: (response) => {
        this.score = response.score;
      },
      error: (error) => {
        console.log(error)
      }
    })
    this.userService.getHint().subscribe({
      next: (response) => {
        this.hint1 = response.hint1
        this.hint2 = response.hint2
        this.hint3 = response.hint3
        this.hint4 = response.hint4
        this.hint5 = response.hint5
        if(this.hint1 != null) {
          this.hint1Status=true;
          this.hintPrice=1500;
        }
        if(this.hint2 != null) {
          this.hint2Status=true;
          this.hintPrice=2000;
        }
        if(this.hint3 != null) {
          this.hint3Status=true;
          this.hintPrice=2500;
        }
        if(this.hint4 != null) {
          this.hint4Status=true;
          this.hintPrice=3000;
        }
        if(this.hint5 != null) {
          this.hint5Status=true;
        }
      },
      error: (error) => {
        console.log(error)
      }
    })
  }

  buyHint(): void{
    let error = document.getElementById("error");
    if(this.score<this.hintPrice) {
      error!.innerText = "You don't have enough point"
      return ;
    }
    this.score-=this.hintPrice
    this.userService.buyHint(this.score).subscribe({
      complete: () => {
        console.log("complete")
        window.location.reload();
      }
    })
  }
  
}
