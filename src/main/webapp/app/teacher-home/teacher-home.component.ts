import {Component, OnInit} from '@angular/core';
import {Response} from '@angular/http';
import {Principal} from '../shared/auth/principal.service';
import {JhiLanguageService, AlertService, EventManager} from 'ng-jhipster';

import {TeacherMySuffix} from './../entities/teacher/teacher-my-suffix.model';

import {TeacherHomeService} from './teacher-home.service';
import {Subscription} from 'rxjs/Subscription';
import {TeacherHomeScheduleComponent} from "./teacher-home.schedule.component";

@Component({
    selector: 'teacher-home',
    templateUrl: './teacher-home.component.html',
    styles: []
})
export class TeacherHomeComponent implements OnInit {
    currentAccount: any;
    eventSubscriber: Subscription;
    name: string;

    constructor(private principal: Principal,
                private jhiLanguageService: JhiLanguageService,
                ) {
        this.jhiLanguageService.setLocations(['teacher-home']);
    }

    ngOnInit() {
    }


}
