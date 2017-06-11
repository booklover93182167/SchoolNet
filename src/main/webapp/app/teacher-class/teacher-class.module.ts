/**
 * Created by User on 07.06.2017.
 */
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';

import { SchoolNetSharedModule } from '../shared';

import {TeacherClassComponent} from "./teacher-class.component";
import {TeacherClassRoute} from "./teacher-class.route";
import {TeacherClassService} from "./teacher-class.service";
import { SortPipe } from "./teacher-class.sort"


    @NgModule({
            imports: [
            CommonModule,
            BrowserModule,
            HttpModule,
            SchoolNetSharedModule,
            RouterModule.forRoot([ TeacherClassRoute ], { useHash: true }),
       ],
        declarations: [
            SortPipe,
           TeacherClassComponent
        ],
        entryComponents: [
            TeacherClassComponent
        ],
        providers: [
            TeacherClassService
       ],
       schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class SchoolNetTeacherClassModule {}
