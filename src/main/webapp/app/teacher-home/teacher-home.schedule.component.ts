import {Component, OnInit} from '@angular/core';
import {Response} from '@angular/http';
import {Principal} from '../shared/auth/principal.service';
import {JhiLanguageService, AlertService, EventManager} from 'ng-jhipster';

import {TeacherMySuffix} from './../entities/teacher/teacher-my-suffix.model';
import {LessonMySuffix} from '../entities/lesson/lesson-my-suffix.model';
import {FormMySuffix} from '../entities/form/form-my-suffix.model';
import {TeacherHomeService} from './teacher-home.service';
import {Subscription} from 'rxjs/Subscription';
import {ScheduleMySuffix} from '../entities/schedule/schedule-my-suffix.model';
import {PupilHomeService} from "../pupil-home/pupil-home.service";
import {ScheduleMySuffixService} from "../entities/schedule/schedule-my-suffix.service";

@Component({
    selector: 'teacher-home-schedule',
    templateUrl: './teacher-home.schedule.component.html',
    styles: []
})
export class TeacherHomeScheduleComponent implements OnInit {
    currentAccount: any;
    currentTeacher: TeacherMySuffix;
    schedules: ScheduleMySuffix[];
    eventSubscriber: Subscription;
    name: string;

    constructor(private principal: Principal,
                private jhiLanguageService: JhiLanguageService,
                private teacherHomeService: TeacherHomeService,
                private alertService: AlertService,
                private eventManager: EventManager) {
        this.jhiLanguageService.setLocations(['teacher-home']);
    }

    ngOnInit() {
        this.loadCurrentTeacher();
        this.registerChangeInSchedules();
    }

    loadCurrentTeacher() {
        this.teacherHomeService.getCurrentTeacher().subscribe(
            (res: Response) => {
                this.currentTeacher = res.json();
                this.loadSchedule(this.currentTeacher.id);
            },
            (res: Response) => this.onError(res.json())
        );
    }

    loadSchedule(teacherId: number) {
        this.teacherHomeService.querySchedule(teacherId).subscribe(
            (res: Response) => {
                this.schedules = res.json();
            },
            (res: Response) => this.onError(res.json())
        );
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    registerChangeInSchedules() {
        this.eventSubscriber = this.eventManager.subscribe('scheduleListModification', (response) => this.loadSchedule(this.currentTeacher.id));
    }
}
