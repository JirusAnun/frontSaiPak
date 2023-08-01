import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  registerFormGroup: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    confirmpassword: new FormControl('', Validators.required)
  })

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  onSubmit(): void {
    
    if(this.registerFormGroup.invalid) return;

    let username = this.registerFormGroup.value.username;
    let password = this.registerFormGroup.value.password;
    let confirmpassword = this.registerFormGroup.value.confirmpassword;

    this.userService.register(username, password, confirmpassword).subscribe({
      next: (response) => {
        if(response.success == true) {
          alert("Success")
        }
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

}
