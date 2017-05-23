/**
 * Created by Kolja on 22.05.2017.
 */
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SchoolNetSharedModule } from '../shared';
import { UserHomeService } from './user-home.service';

import { USER_HOME_ROUTE, UserHomeComponent } from './';

@NgModule({
    imports: [
        SchoolNetSharedModule,
        RouterModule.forRoot([ USER_HOME_ROUTE ], { useHash: true })
    ],
    declarations: [
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
