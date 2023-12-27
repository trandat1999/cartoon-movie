import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LayoutComponent} from "./pages/layout/layout.component";
import {LoginComponent} from "./pages/auth/login/login.component";
import {RegisterComponent} from "./pages/auth/register/register.component";
import {ForbiddenComponent} from "./pages/auth/forbidden/forbidden.component";
import {ForgotPasswordComponent} from "./pages/auth/forgot-password/forgot-password.component";
import {NotFoundComponent} from "./pages/auth/not-found/not-found.component";

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/welcome' },
  { path: '', component: LayoutComponent,
    children: [
      { path: 'welcome', loadChildren: () => import('./pages/welcome/welcome.module').then(m => m.WelcomeModule) }
    ]
  },
  {path: "login", component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: "forbidden", component: ForbiddenComponent},
  {path: "forgot-password", component: ForgotPasswordComponent},
  {path: "**", component: NotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
