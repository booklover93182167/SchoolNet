/**
 * Created by Kolja on 22.05.2017.
 */
import {Component, OnInit} from '@angular/core';

import {Principal} from '../shared';
import {UserHomeService} from "./user-home.service";
import { EventManager, JhiLanguageService } from 'ng-jhipster';

@Component({
    selector: 'user-home',
    templateUrl: 'user-home.component.html',
    styleUrls: ['user-home.component.css'],
})
export class UserHomeComponent implements OnInit{

    account: any;

    constructor(private principal: Principal,
                private jhiLanguageService: JhiLanguageService,
                private userHomeService: UserHomeService) {
        this.jhiLanguageService.setLocations(['home, user-home']);
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
    }




}
