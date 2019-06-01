import { NgModule }                     from '@angular/core';
import { RouterModule, Routes }         from '@angular/router';

import { MenuComponent }           from './menu.component';

const routes: Routes = [
  { path: '', redirectTo: '/menu/3238', pathMatch: 'full' },
  { path: 'menu/:id', component: MenuComponent },
]

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}