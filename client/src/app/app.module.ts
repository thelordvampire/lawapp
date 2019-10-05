import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

// used to create fake backend
import { fakeBackendProvider } from './_helpers';

import { appRoutingModule } from './app.routing';
import { JwtInterceptor, ErrorInterceptor } from './_helpers';
import { AppComponent } from './app.component';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { AlertComponent } from './_components';
import { HomeComponent } from './main-component/home';
import { PracticeComponent } from './main-component/practice/practice.component';
import { ResourcesComponent } from './main-component/resources/resources.component';
import { TypoComponent } from './main-component/typo/typo.component';
import { ContactComponent } from './main-component/contact/contact.component';
import { HeaderComponent } from './main-component/header/header.component';
import { FooterComponent } from './main-component/footer/footer.component';
import { AdminComponent } from './admin/admin.component';
import { AppService } from './app.service';
import { ChatComponent } from './main-component/chat/chat.component';
import { FormsModule } from '@angular/forms';
import { NgxCarouselModule } from 'ngx-carousel';
import 'hammerjs';
import { ShareComponent } from './share/share.component';
import { NewsComponent } from './main-component/news/news.component';
import { NewsDetailComponent } from './main-component/news/component/news-detail/news-detail.component';
import { AdminNewsComponent } from './admin/component/admin-news/admin-news.component';
import { NgxTinymceModule } from 'ngx-tinymce';
import { NgxEditorModule } from 'ngx-editor';
import { ProfileComponent } from './main-component/profile/profile.component';
import { LawyerListComponent } from './main-component/lawyer-list/lawyer-list.component';
import { FieldComponent } from './main-component/field/field.component';
import { AdminNewsMyComponent } from './admin/component/admin-news-my/admin-news-my.component';
import {NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';
@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        HttpClientModule,
        appRoutingModule,
        FormsModule,
        NgxCarouselModule,
        NgMultiSelectDropDownModule.forRoot(),
        // NgxEditorModule,
        NgxTinymceModule.forRoot({
            // or cdn
            baseURL: '//cdnjs.cloudflare.com/ajax/libs/tinymce/4.9.0/'
          })
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        LoginComponent,
        RegisterComponent,
        AlertComponent,
        PracticeComponent,
        NewsComponent,
        ResourcesComponent,
        TypoComponent,
        ContactComponent,
        HeaderComponent,
        FooterComponent,
        AdminComponent,
        ChatComponent,
        ShareComponent,
        NewsDetailComponent,
        AdminNewsComponent,
        ProfileComponent,
        LawyerListComponent,
        FieldComponent,
        AdminNewsMyComponent,
    ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
        // provider used to create fake backend
        fakeBackendProvider
    ],
    bootstrap: [AppComponent],

})
export class AppModule { };
