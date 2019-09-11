import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { AuthGuard } from './_helpers';
import { AppComponent } from './app.component';
import { HomeComponent } from './main-component/home';
import { PracticeComponent } from './main-component/practice/practice.component';
import { ResourcesComponent } from './main-component/resources/resources.component';
import { TypoComponent } from './main-component/typo/typo.component';
import { ContactComponent } from './main-component/contact/contact.component';
import { AdminComponent } from './admin/admin.component';
import { ChatComponent } from './main-component/chat/chat.component';
import { NewsComponent } from './main-component/news/news.component';
import { NewsDetailComponent } from './main-component/news/component/news-detail/news-detail.component';
import { AdminNewsComponent } from './admin/component/admin-news/admin-news.component';
import { ProfileComponent } from './main-component/profile/profile.component';

const routes: Routes = [

    { path: '', component: HomeComponent },
    { path: 'home', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'practice', component: PracticeComponent },
    { path: 'news', component: NewsComponent,},
    { path: 'news/:id', component: NewsDetailComponent },
    { path: 'profile/:id', component: ProfileComponent },
    { path: 'resources', component: ResourcesComponent },
    { path: 'typo', component: TypoComponent },
    { path: 'contact', component: ContactComponent },
    { path: 'admin', component: AdminComponent, canActivate: [AuthGuard], children: [
      {
        path: 'news',
        component: AdminNewsComponent
      }] 
    },
  //  { path: 'chat', component: ChatComponent },

  // , canActivate: [AuthGuard]
    // otherwise redirect to home
    { path: '**', redirectTo: 'home' }
];

const adminRouter: Routes = [
    
]

export const appRoutingModule = RouterModule.forRoot(routes);