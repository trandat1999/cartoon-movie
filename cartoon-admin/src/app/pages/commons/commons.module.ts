import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BreadcrumbComponent } from './breadcrumb/breadcrumb.component';
import {NzBreadCrumbModule} from "ng-zorro-antd/breadcrumb";
import {NzIconModule} from "ng-zorro-antd/icon";
import {RouterLinkWithHref} from "@angular/router";



@NgModule({
  declarations: [
    BreadcrumbComponent
  ],
  exports: [
    BreadcrumbComponent
  ],
  imports: [
    CommonModule,
    NzBreadCrumbModule,
    NzIconModule,
    RouterLinkWithHref
  ]
})
export class CommonsModule { }
