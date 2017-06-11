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
import {FormControl, FormGroup} from "@angular/forms";

@Component({
    selector: 'teacher-home-schedule',
    templateUrl: './teacher-home.schedule.component.html',
    styles: []
})
export class TeacherHomeScheduleComponent implements OnInit {
    form;
    currentAccount: any;
    currentTeacher: TeacherMySuffix;
    schedules: ScheduleMySuffix[];
    filteredSchedules: ScheduleMySuffix[] = [];
    eventSubscriber: Subscription;
    lessons: LessonMySuffix[];
    forms: FormMySuffix[];
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
                this.filteredSchedules = (this.isFilterOn) ?
                    this.teacherHomeService.loadByLastFilter(this.filteredSchedules, this.schedules) : this.schedules;
            },
            (res: Response) => this.onError(res.json())
        );
    }

    onChangeDate(value) {
        this.filteredSchedules = this.teacherHomeService.filterByDate(new Date(value.toString()),
            (this.isFilterOn) ? this.filteredSchedules : this.schedules);
        if (!this.isFilterOn) {
            this.isFilterOn = true;
        }
    }

    onChangeLesson(value) {
        this.filteredSchedules = this.teacherHomeService.filterByLesson(parseInt(value, 10),
            (this.isFilterOn) ? this.filteredSchedules : this.schedules);
        if (!this.isFilterOn) {
            this.isFilterOn = true;
        }
    }

    onChangeForm(value) {
        this.filteredSchedules = this.teacherHomeService.filterByForm(parseInt(value, 10),
            (this.isFilterOn) ? this.filteredSchedules : this.schedules);
        if (!this.isFilterOn) {
            this.isFilterOn = true;
        }
    }

    clear() {
        this.filteredSchedules = this.schedules;
        this.isFilterOn = false;
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    registerChangeInSchedules() {
        this.eventSubscriber = this.eventManager.subscribe('scheduleListModification',
            (response) => this.loadSchedule(this.currentTeacher.id));
    }
}
