import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'thd-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  constructor() {}
  ngOnInit(): void {
    this.formGroup = new FormGroup({
      username: new FormControl("",[Validators.required]),
      password: new FormControl("",[Validators.required]),
      remember: new FormControl(true),
    })
  }
  formGroup : FormGroup;
  submitForm(): void {
    if (this.formGroup.valid) {
      console.log('submit', this.formGroup.value);
    } else {
      Object.values(this.formGroup.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }
}
