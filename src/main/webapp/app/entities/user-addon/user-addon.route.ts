import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { UserAddonComponent } from './user-addon.component';
import { UserAddonDetailComponent } from './user-addon-detail.component';
import { UserAddonPopupComponent } from './user-addon-dialog.component';
import { UserAddonDeletePopupComponent } from './user-addon-delete-dialog.component';

import { Principal } from '../../shared';

export const userAddonRoute: Routes = [
    {
        path: 'user-addon',
        component: UserAddonComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'schoolNetApp.userAddon.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-addon/:id',
        component: UserAddonDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'schoolNetApp.userAddon.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userAddonPopupRoute: Routes = [
    {
        path: 'user-addon-new',
        component: UserAddonPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'schoolNetApp.userAddon.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-addon/:id/edit',
        component: UserAddonPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'schoolNetApp.userAddon.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-addon/:id/delete',
        component: UserAddonDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'schoolNetApp.userAddon.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
