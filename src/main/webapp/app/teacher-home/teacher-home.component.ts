import {Component, OnInit} from '@angular/core';
import {Response} from '@angular/http';
import {Principal} from '../shared/auth/principal.service';
import {JhiLanguageService, AlertService, EventManager} from 'ng-jhipster';

import {TeacherMySuffix} from './../entities/teacher/teacher-my-suffix.model';

import {TeacherHomeService} from './teacher-home.service';
import {Subscription} from 'rxjs/Subscription';

@Component({
    selector: 'teacher-home',
    templateUrl: './teacher-home.component.html',
    styles: []
})
export class TeacherHomeComponent implements OnInit {
    currentAccount: any;
    currentTeacher: TeacherMySuffix;
    eventSubscriber: Subscription;
    name: string;

    constructor(private principal: Principal,
                private jhiLanguageService: JhiLanguageService,
                private teacherHomeService: TeacherHomeService,
                private alertService: AlertService) {
        this.jhiLanguageService.setLocations(['teacher-home']);
    }

    ngOnInit() {
        this.loadCurrentTeacher();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
    }

    loadCurrentTeacher() {
        this.teacherHomeService.getCurrentTeacher().subscribe(
            (res: Response) => {
                this.currentTeacher = res.json();
            },
            (res: Response) => this.onError(res.json())
        );
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
