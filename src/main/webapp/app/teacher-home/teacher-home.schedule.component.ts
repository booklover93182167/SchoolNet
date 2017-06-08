import {Component, OnInit} from '@angular/core';
import {Response} from '@angular/http';
import {JhiLanguageService, AlertService, EventManager} from 'ng-jhipster';

import {TeacherMySuffix} from './../entities/teacher/teacher-my-suffix.model';

import {TeacherHomeService} from './teacher-home.service';
import {Subscription} from 'rxjs/Subscription';
import {ScheduleMySuffix} from '../entities/schedule/schedule-my-suffix.model';
import {LessonMySuffix} from '../entities/lesson/lesson-my-suffix.model';
import {FormMySuffix} from '../entities/form/form-my-suffix.model';
import {isUndefined} from 'util';

@Component({
    selector: 'teacher-home-schedule',
    templateUrl: './teacher-home.schedule.component.html',
    styles: []
})
export class TeacherHomeScheduleComponent implements OnInit {
    currentAccount: any;
    currentTeacher: TeacherMySuffix;
    schedules: ScheduleMySuffix[];
    filteredSchedules: ScheduleMySuffix[] = [];
    eventSubscriber: Subscription;
    lessons: LessonMySuffix[];
    forms: FormMySuffix[];
    selectedLessonId: number;
    selectedFormId: number;
    selectedDate: Date;
    isFilterOn: boolean;

    constructor(private jhiLanguageService: JhiLanguageService,
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
                this.loadLessons(this.currentTeacher.id);
            },
            (res: Response) => this.onError(res.json())
        );
    }

    loadLessons(teacherId: number) {
        this.teacherHomeService.queryLessons(teacherId).subscribe(
            (res: Response) => {
                this.lessons = res.json();
                this.loadForms(this.currentTeacher.id);
            });
    }

    loadForms(teacherId: number) {
        this.teacherHomeService.queryForm(teacherId).subscribe(
            (res: Response) => {
                this.forms = res.json();
                this.loadSchedule(this.currentTeacher.id);
            },
            (res: Response) => this.onError(res.json())
        );
    }

    loadSchedule(teacherId: number) {
        this.teacherHomeService.querySchedule(teacherId).subscribe(
            (res: Response) => {
                this.schedules = res.json();
                if (!this.isFilterOn) {
                    this.filteredSchedules = this.schedules;
                } else {
                    this.filteredSchedules = this.teacherHomeService.filterSchedule(parseInt(String(this.selectedLessonId), 10),
                        parseInt(String(this.selectedFormId), 10), this.schedules,
                        isUndefined(this.selectedDate) ? this.selectedDate : new Date(this.selectedDate.toString()));
                }
            },
            (res: Response) => this.onError(res.json())
        );
    }

    onClick() {
        if (isUndefined(this.selectedLessonId) && isUndefined(this.selectedFormId) && isUndefined(this.selectedDate)) {
            return;
        } else {
            this.isFilterOn = true;
            this.filteredSchedules = this.teacherHomeService.filterSchedule(parseInt(String(this.selectedLessonId), 10),
                parseInt(String(this.selectedFormId), 10), this.schedules,
                isUndefined(this.selectedDate) ? this.selectedDate : new Date(this.selectedDate.toString()));
        }
    }

    Clear() {
        this.filteredSchedules = this.schedules;
        this.isFilterOn = false;
        this.selectedLessonId = undefined;
        this.selectedFormId = undefined;
        this.selectedDate = undefined;
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    registerChangeInSchedules() {
        this.eventSubscriber = this.eventManager.subscribe('scheduleListModification',
            (response) => this.loadSchedule(this.currentTeacher.id));
    }
}
