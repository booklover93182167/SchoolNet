/**
 * Created by inva on 23-May-17.
 */

import { Route } from '@angular/router';
import { UserRouteAccessService } from '../../shared';
import { UserHomeSchedulesComponent} from "./user-home-schedules.component";

export const USER_HOME_SCHEDULES_ROUTE: Route = {
    path: 'user-home/:formid/:month',
        component:
        UserHomeSchedulesComponent,
        data:
        {
            authorities: ['ROLE_USER'],
            pageTitle: 'schoolNetApp.schedule.home.title'
        },
    canActivate: [UserRouteAccessService]
};
