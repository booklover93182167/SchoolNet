/**
 * Created by slavkosoltys on 28.05.17.
 */
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';

import { SchoolNetSharedModule } from '../shared';

import {TeacherHomeComponent} from './teacher-home.component';
import {TeacherHomeRoute} from './teacher-home.route';
import {TeacherHomeService} from './teacher-home.service';
import {TeacherHomeScheduleComponent} from './teacher-home.schedule.component';
import {LengthPipe, TeacherScheduleSortByDatePipe} from './teacher-home.PipeUtil';

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        HttpModule,
        SchoolNetSharedModule,
        RouterModule.forRoot([ TeacherHomeRoute ], { useHash: true }),
    ],
    declarations: [
        TeacherScheduleSortByDatePipe,
        LengthPipe,
        TeacherHomeComponent,
        TeacherHomeScheduleComponent
    ],
    entryComponents: [
        TeacherHomeComponent,
    ],
    providers: [
        TeacherHomeService,
        TeacherHomeScheduleComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class SchoolNetTeacherHomeModule {}
