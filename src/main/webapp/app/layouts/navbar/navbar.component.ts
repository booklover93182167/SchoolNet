import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiLanguageService } from 'ng-jhipster';

import { ProfileService } from '../profiles/profile.service'; // FIXME barrel doesn't work here
import { Account, JhiLanguageHelper, Principal, LoginModalService, LoginService } from '../../shared';
import { UserAddon } from '../../entities/user-addon/user-addon.model';
import { UserAddonService } from '../../entities/user-addon/user-addon.service';

import { VERSION, DEBUG_INFO_ENABLED } from '../../app.constants';

@Component({
    selector: 'jhi-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: [
        'navbar.css'
    ]
})
export class NavbarComponent implements OnInit {

    account: Account;
    inProduction: boolean;
    isNavbarCollapsed: boolean;
    languages: any[];
    swaggerEnabled: boolean;
    modalRef: NgbModalRef;
    version: string;
    userAddon: UserAddon;

    constructor(
        private loginService: LoginService,
        private languageHelper: JhiLanguageHelper,
        private languageService: JhiLanguageService,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private profileService: ProfileService,
        private router: Router,
        private userAddonService: UserAddonService
    ) {
        this.version = DEBUG_INFO_ENABLED ? 'v' + VERSION : '';
        this.isNavbarCollapsed = true;
        this.languageService.addLocation('home');
    }

    ngOnInit() {
        this.account = null;
        this.userAddon = null;

        this.languageHelper.getAll().then((languages) => {
            this.languages = languages;
        });

        this.profileService.getProfileInfo().subscribe((profileInfo) => {
            this.inProduction = profileInfo.inProduction;
            this.swaggerEnabled = profileInfo.swaggerEnabled;
        });
    }

    changeLanguage(languageKey: string) {
      this.languageService.changeLanguage(languageKey);
    }

    collapseNavbar() {
        this.isNavbarCollapsed = true;
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    isAccount() {
        if (this.principal.isAuthenticated() && this.account == null) {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        }

        if (this.account && this.userAddon == null) {
            this.userAddonService.findMy().subscribe((userAddon) => {
                this.userAddon = userAddon;
            });
        }

        if (this.account) {
            return true;
        }

        return false;
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    logout() {
        this.account = null;
        this.userAddon = null;
        this.collapseNavbar();
        this.loginService.logout();
        this.router.navigate(['']);
        document.cookie = `JWT-TOKEN=null; expires=0`;
    }

    toggleNavbar() {
        this.isNavbarCollapsed = !this.isNavbarCollapsed;
    }

    getImageUrl() {
        return (this.isAuthenticated() && this.userAddon) ? this.userAddon.image : null;
    }
}
