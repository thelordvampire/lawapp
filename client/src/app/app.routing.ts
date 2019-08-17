import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { AuthGuard } from './_helpers';
import { AppComponent } from './app.component';
import { HomeComponent } from './main-component/home';
import { PracticeComponent } from './main-component/practice/practice.component';
import { ClientsComponent } from './main-component/clients/clients.component';
import { ResourcesComponent } from './main-component/resources/resources.component';
import { TypoComponent } from './main-component/typo/typo.component';
import { ContactComponent } from './main-component/contact/contact.component';
import { AdminComponent } from './admin/admin.component';

const routes: Routes = [

    { path: '', component: HomeComponent },
    { path: 'home', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'practice', component: PracticeComponent },
    { path: 'clients', component: ClientsComponent },
    { path: 'resources', component: ResourcesComponent },
    { path: 'typo', component: TypoComponent },
    { path: 'contact', component: ContactComponent },
    { path: 'admin', component: AdminComponent, canActivate: [AuthGuard] },

    // otherwise redirect to home
    { path: '**', redirectTo: 'home' }
];

export const appRoutingModule = RouterModule.forRoot(routes);