import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SchoolNetSharedModule } from '../../shared';
import {
    TeacherManagementService,
    TeacherManagementComponent,
    TEACHER_ROUTE,
} from './';

const ENTITY_STATES = [
    ...TEACHER_ROUTE,
];

@NgModule({
    imports: [
        SchoolNetSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TeacherManagementComponent,
    ],
    entryComponents: [
        TeacherManagementComponent,
    ],
    providers: [
        TeacherManagementService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolNetTeacherManagementModule {}
