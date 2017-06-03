import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SchoolNetSharedModule } from '../../shared';
import {
    ScheduleMySuffixService,
    ScheduleMySuffixPopupService,
    ScheduleMySuffixComponent,
    ScheduleMySuffixDetailComponent,
    ScheduleMySuffixDialogComponent,
    ScheduleMySuffixPopupComponent,
    ScheduleMySuffixDeletePopupComponent,
    ScheduleMySuffixDeleteDialogComponent,
    scheduleRoute,
    schedulePopupRoute,
} from './';
import {
    ScheduleHomeworkDialogComponent,
    ScheduleMySuffixPopupComponentHomework
} from './schedule-my-suffix-dialog.homework.edit.component';
import {TeacherHomePopupService} from "../../teacher-home/teacher-home-popup.service";

const ENTITY_STATES = [
    ...scheduleRoute,
    ...schedulePopupRoute,
];

@NgModule({
    imports: [
        SchoolNetSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ScheduleMySuffixComponent,
        ScheduleMySuffixDetailComponent,
        ScheduleMySuffixDialogComponent,
        ScheduleHomeworkDialogComponent,
        ScheduleMySuffixPopupComponentHomework,
        ScheduleMySuffixDeleteDialogComponent,
        ScheduleMySuffixPopupComponent,
        ScheduleMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        ScheduleMySuffixComponent,
        ScheduleMySuffixDialogComponent,
        ScheduleMySuffixPopupComponentHomework,
        ScheduleMySuffixPopupComponent,
        ScheduleMySuffixDeleteDialogComponent,
        ScheduleMySuffixDeletePopupComponent,
        ScheduleHomeworkDialogComponent
    ],
    providers: [
        ScheduleMySuffixService,
        ScheduleMySuffixPopupService,
        TeacherHomePopupService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolNetScheduleMySuffixModule {}
