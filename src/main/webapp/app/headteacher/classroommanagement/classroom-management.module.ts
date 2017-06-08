import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SchoolNetSharedModule } from '../../shared';
import {SchoolNetAdminModule} from "../../admin/admin.module";
import {
    ClassroomManagementService,
    ClassroomManagementPopupService,
    ClassroomManagementComponent,
    ClassroomManagementDetailComponent,
    ClassroomManagementDialogComponent,
    ClassroomManagementPopupComponent,
    ClassroomManagementDeletePopupComponent,
    ClassroomManagementDeleteDialogComponent,
    classroomRoute,
    classroomPopupRoute,
} from './';

const ENTITY_STATES = [
    ...classroomRoute,
    ...classroomPopupRoute,
];

@NgModule({
    imports: [
        SchoolNetSharedModule,
        SchoolNetAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ClassroomManagementComponent,
        ClassroomManagementDetailComponent,
        ClassroomManagementDialogComponent,
        ClassroomManagementDeleteDialogComponent,
        ClassroomManagementPopupComponent,
        ClassroomManagementDeletePopupComponent,
    ],
    entryComponents: [
        ClassroomManagementComponent,
        ClassroomManagementDialogComponent,
        ClassroomManagementPopupComponent,
        ClassroomManagementDeleteDialogComponent,
        ClassroomManagementDeletePopupComponent,
    ],
    providers: [
        ClassroomManagementService,
        ClassroomManagementPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolNetClassroomManagementModule {}
