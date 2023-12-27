import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor() { }
  signOut(): void {
    localStorage.clear();
  }
  public saveToken(): void {

  }

  public getToken(): string | null {
    return null
  }

  public getRefreshToken(): string | null {
    return null
  }

  public getUsername(): string | null {
    return null
  }

  public saveUser(user: any): void {
    return null
  }

  public getAccessToken() {
    if (this.getToken() == null) {
      return ""
    } else {
      return this.getToken() as string;
    }
  }
  public setLanguage(language : string) {
    // localStorage.removeItem(AppSettings.LANGUAGE);
    // if(language) {
    //   localStorage.setItem(AppSettings.LANGUAGE,language);
    // }else{
    //   localStorage.setItem(AppSettings.LANGUAGE,AppSettings.LANGUAGE_EN);
    // }
  }
  public getLanguage() {
    // let lang = localStorage.getItem(AppSettings.LANGUAGE);
    // if(lang){
    //   return lang;
    // }else{
    //   localStorage.setItem(AppSettings.LANGUAGE,AppSettings.LANGUAGE_EN);
    //   return AppSettings.LANGUAGE_EN;
    // }
  }
}
