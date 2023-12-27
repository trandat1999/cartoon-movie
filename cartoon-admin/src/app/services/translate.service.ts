import { Injectable } from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {StorageService} from "./storage.service";
import {NzI18nService} from "ng-zorro-antd/i18n";

@Injectable({
  providedIn: 'root'
})
export class TranslateConfigService {

  constructor(private translate: TranslateService,private nzI18nService: NzI18nService,
  private localStorage : StorageService) {
    // this.translate.use(this.localStorage.getLanguage())
    // this.adapterDate.setLocale(this.localStorage.getLanguage());
    // this.nzI18nService.setLocale()
  }

  changeLanguage(language: string){
    this.localStorage.setLanguage(language);
    // this.translate.use(this.localStorage.getLanguage());
    // this.adapterDate.setLocale(this.localStorage.getLanguage());
  }

  getLanguage(){
    return this.localStorage.getLanguage();
  }
}
