import { Routes } from '@angular/router';
import { UserRouteAccessService } from '../shared';
import { TeacherScheduleComponent } from './';

export const teacherScheduleRoute: Routes = [
    {
        path: 'teacher-schedule',
        component: TeacherScheduleComponent,
        data: {
            authorities: ['ROLE_TEACHER'],
            pageTitle: 'global.menu.teacher-schedule'
        },
        canActivate: [UserRouteAccessService]
    }
];
