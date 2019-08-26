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
import { ClientsComponent } from './main-component/clients/clients.component';
import { ResourcesComponent } from './main-component/resources/resources.component';
import { TypoComponent } from './main-component/typo/typo.component';
import { ContactComponent } from './main-component/contact/contact.component';
import { HeaderComponent } from './main-component/header/header.component';
import { FooterComponent } from './main-component/footer/footer.component';
import { AdminComponent } from './admin/admin.component';
import { AppService } from './app.service';
import { ChatComponent } from './main-component/chat/chat.component';
import { FormsModule } from '@angular/forms';

@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        HttpClientModule,
        appRoutingModule,
        FormsModule
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        LoginComponent,
        RegisterComponent,
        AlertComponent,
        PracticeComponent,
        ClientsComponent,
        ResourcesComponent,
        TypoComponent,
        ContactComponent,
        HeaderComponent,
        FooterComponent,
        AdminComponent,
        ChatComponent,
    ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
        // provider used to create fake backend
        fakeBackendProvider
    ],
    bootstrap: [AppComponent]
})
export class AppModule { };