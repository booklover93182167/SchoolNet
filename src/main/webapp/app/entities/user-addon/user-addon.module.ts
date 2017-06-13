import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SchoolNetSharedModule } from '../../shared';
import { SchoolNetAdminModule } from '../../admin/admin.module';
import {
    UserAddonService,
    UserAddonPopupService,
    UserAddonComponent,
    UserAddonDetailComponent,
    UserAddonDialogComponent,
    UserAddonPopupComponent,
    UserAddonDeletePopupComponent,
    UserAddonDeleteDialogComponent,
    userAddonRoute,
    userAddonPopupRoute,
} from './';

const ENTITY_STATES = [
    ...userAddonRoute,
    ...userAddonPopupRoute,
];

@NgModule({
    imports: [
        SchoolNetSharedModule,
        SchoolNetAdminModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        UserAddonComponent,
        UserAddonDetailComponent,
        UserAddonDialogComponent,
        UserAddonDeleteDialogComponent,
        UserAddonPopupComponent,
        UserAddonDeletePopupComponent,
    ],
    entryComponents: [
        UserAddonComponent,
        UserAddonDialogComponent,
        UserAddonPopupComponent,
        UserAddonDeleteDialogComponent,
        UserAddonDeletePopupComponent,
    ],
    providers: [
        UserAddonService,
        UserAddonPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SchoolNetUserAddonModule {}
