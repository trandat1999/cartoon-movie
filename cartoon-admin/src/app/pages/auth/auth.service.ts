import { Injectable } from '@angular/core';
import {BaseService} from "../../services/base.service";
import {AuthRequest} from "./auth.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService{
  private readonly apiUrl: string = "/api/v1/auth"
  constructor(private base : BaseService) {
  }
  login(request:AuthRequest){
    return this.base.post(this.apiUrl+"/login",request);
  }

}
