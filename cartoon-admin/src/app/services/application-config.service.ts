import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApplicationConfigService {
  private appConfig: any;
  constructor(private http: HttpClient) { }
  loadAppConfig() {
    return this.http.get(environment.fileConfig)
      .toPromise()
      .then(config => {
        this.appConfig = config;
      });
  }
  get apiBaseUrl(): string {
    return this.appConfig.server_url;
  }
}
