/**
 * Created by Kolja on 22.05.2017.
 */
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CalendarModule } from 'angular-calendar';
import { CalendarComponent } from './calendar-component/calendar.component';

import { SchoolNetSharedModule } from '../shared';
import { UserHomeService } from './user-home.service';

import { USER_HOME_ROUTE, UserHomeComponent } from './';

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        HttpModule,
        BrowserAnimationsModule,
        CalendarModule.forRoot(),
        SchoolNetSharedModule,
        RouterModule.forRoot([ USER_HOME_ROUTE ], { useHash: true })
    ],
    declarations: [
        CalendarComponent,
        UserHomeComponent,
    ],
    entryComponents: [
    ],
    providers: [
        UserHomeService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class SchoolNetUserHomeModule {}
