/**
 * Created by Kolja on 22.05.2017.
 */
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SchoolNetSharedModule } from '../shared';
import { UserHomeService } from './user-home.service';

import { USER_HOME_ROUTE, USER_HOME_SCHEDULES_ROUTE, UserHomeComponent, UserHomeSchedulesComponent } from './';

@NgModule({
    imports: [
        SchoolNetSharedModule,
        RouterModule.forRoot([ USER_HOME_ROUTE ], { useHash: true }),
        RouterModule.forRoot([ USER_HOME_SCHEDULES_ROUTE ], { useHash: true })
    ],
    declarations: [
        UserHomeComponent,
        UserHomeSchedulesComponent,
    ],
    entryComponents: [
        UserHomeComponent,
        UserHomeSchedulesComponent
    ],
    providers: [
        UserHomeService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class SchoolNetUserHomeModule {}
