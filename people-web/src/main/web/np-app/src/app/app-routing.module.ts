import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchPeopleComponent } from './search/search-people.component';
import { PersonComponent } from './person/person.component';

const routes: Routes = [
  { path: 'app', redirectTo: '/app/search', pathMatch: 'full' },
  { path: 'app/search', component: SearchPeopleComponent },
  { path: 'app/people', component: PersonComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}