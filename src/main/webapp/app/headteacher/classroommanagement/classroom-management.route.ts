import {Injectable} from '@angular/core';
import {Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate} from '@angular/router';

import {UserRouteAccessService} from '../../shared';
import {PaginationUtil} from 'ng-jhipster';

import {ClassroomManagementComponent} from './classroom-management.component';
import {ClassroomManagementDetailComponent} from './classroom-management-detail.component';
import {ClassroomManagementPopupComponent} from './classroom-management-dialog.component';
import {ClassroomManagementDeletePopupComponent} from './classroom-management-delete-dialog.component';

import {Principal} from '../../shared';

export const classroomRoute: Routes = [
    {
        path: 'classroom-management',
        component: ClassroomManagementComponent,
        data: {
            authorities: ['ROLE_HEAD_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'schoolNetApp.classroom.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'classroom-management/:id',
        component: ClassroomManagementDetailComponent,
        data: {
            authorities: ['ROLE_HEAD_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'schoolNetApp.classroom.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const classroomPopupRoute: Routes = [
    {
        path: 'classroom-management-popup',
        component: ClassroomManagementPopupComponent,
        data: {
            authorities: ['ROLE_HEAD_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'schoolNetApp.classroom.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'classroom-management/:id/edit',
        component: ClassroomManagementPopupComponent,
        data: {
            authorities: ['ROLE_HEAD_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'schoolNetApp.classroom.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'classroom-management/:id/delete',
        component: ClassroomManagementDeletePopupComponent,
        data: {
            authorities: ['ROLE_HEAD_TEACHER', 'ROLE_ADMIN'],
            pageTitle: 'schoolNetApp.classroom.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
