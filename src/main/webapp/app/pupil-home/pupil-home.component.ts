/**
 * Created by Kolja on 22.05.2017.
 */
import {Component, OnInit} from '@angular/core';
import { Response } from '@angular/http';
import {Principal} from '../shared';
import {PupilHomeService} from "./pupil-home.service";
import { EventManager, JhiLanguageService } from 'ng-jhipster';

@Component({
    selector: 'pupil-home',
    templateUrl: 'pupil-home.component.html',
    styleUrls: ['pupil-home.component.css'],
})
export class PupilHomeComponent implements OnInit{
    account: any;

    constructor(private principal: Principal,
                private jhiLanguageService: JhiLanguageService,
                private pupilHomeService: PupilHomeService) {
        this.jhiLanguageService.setLocations(['home']);
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
    }

}
