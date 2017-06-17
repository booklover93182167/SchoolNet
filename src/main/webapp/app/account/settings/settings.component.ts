import { Component, OnInit } from '@angular/core';
// import { JhiLanguageService, DataUtils } from 'ng-jhipster';
import { JhiLanguageService } from 'ng-jhipster';

import { Principal, AccountService, JhiLanguageHelper } from '../../shared';

import { UserAddon } from '../../entities/user-addon/user-addon.model';
import { UserAddonService } from '../../entities/user-addon/user-addon.service';

@Component({
    selector: 'jhi-settings',
    templateUrl: './settings.component.html'
})
export class SettingsComponent implements OnInit {
    error: string;
    success: string;
    settingsAccount: any;
    languages: any[];
    userAddon: UserAddon;

    constructor(
        private account: AccountService,
        private principal: Principal,
        // private dataUtils: DataUtils,
        private languageService: JhiLanguageService,
        private languageHelper: JhiLanguageHelper,
        private userAddonService: UserAddonService
    ) {
        this.languageService.setLocations(['settings']);
    }

    ngOnInit() {
        this.userAddonService.findMy().subscribe((userAddon) => {
            this.userAddon = userAddon;
        });
        this.principal.identity().then((account) => {
            this.settingsAccount = this.copyAccount(account);
        });
        this.languageHelper.getAll().then((languages) => {
            this.languages = languages;
        });
    }

    save() {
        this.userAddonService.saveMy(this.userAddon).subscribe((res: UserAddon) => {
            this.userAddon = res;
        });

        this.account.save(this.settingsAccount).subscribe(() => {
            this.error = null;
            this.success = 'OK';
            this.principal.identity(true).then((account) => {
                this.settingsAccount = this.copyAccount(account);
            });
            this.languageService.getCurrent().then((current) => {
                if (this.settingsAccount.langKey !== current) {
                    this.languageService.changeLanguage(this.settingsAccount.langKey);
                }
            });
        }, () => {
            this.success = null;
            this.error = 'ERROR';
        });
    }

    // byteSize(field) {
    //     return this.dataUtils.byteSize(field);
    // }
    //
    // setFileData(event, userAddon, field, isImage) {
    //     if (event.target.files && event.target.files[0]) {
    //         const file = event.target.files[0];
    //         if (isImage && !/^image\//.test(file.type)) {
    //             return;
    //         }
    //         this.dataUtils.toBase64(file, (base64Data) => {
    //             userAddon[field] = base64Data;
    //             userAddon[`${field}ContentType`] = file.type;
    //         });
    //     }
    // }

    copyAccount(account) {
        return {
            activated: account.activated,
            email: account.email,
            firstName: account.firstName,
            langKey: account.langKey,
            lastName: account.lastName,
            login: account.login,
            imageUrl: account.imageUrl
        };
    }
}
