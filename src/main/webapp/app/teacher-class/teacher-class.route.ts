/**
 * Created by User on 07.06.2017.
 */
import { Route } from '@angular/router';

    import { UserRouteAccessService } from '../shared';
import {TeacherClassComponent} from "./teacher-class.component";

    export const TeacherClassRoute: Route = {
        path: 'teacher-class',
        component: TeacherClassComponent,
        data:
        {
           authorities: ['ROLE_TEACHER'],

            pageTitle: 'schoolNetApp.teacher-class.myClass'
          },
    canActivate: [UserRouteAccessService]
};
