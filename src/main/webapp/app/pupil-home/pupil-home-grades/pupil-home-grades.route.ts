/**
 * Created by inva on 31-May-17.
 */
import { Route } from '@angular/router';

import { PupilHomeGradesComponent } from '../.';
import {UserRouteAccessService} from "../../shared/auth/user-route-access-service";

export const PUPIL_HOME_GRADES_ROUTE: Route = {
    path: 'pupil-home-grades',
    component:
    PupilHomeGradesComponent,
    data:
        {
            authorities: ['ROLE_PUPIL'],
        },
    canActivate: [UserRouteAccessService]
};
