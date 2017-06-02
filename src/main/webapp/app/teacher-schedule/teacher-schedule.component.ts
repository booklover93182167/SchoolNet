import { Component, OnInit } from '@angular/core';
import { Response } from '@angular/http';
import { JhiLanguageService, AlertService } from 'ng-jhipster';

import { ScheduleMySuffix } from './../entities/schedule/';
import { TeacherMySuffix } from '../entities/teacher/teacher-my-suffix.model';

import { TeacherScheduleService } from './teacher-schedule.service';

import { Principal } from '../shared';

@Component({
    selector: 'jhi-teacher-schedule',
    templateUrl: './teacher-schedule.component.html',
    styleUrls: ['./teacher-schedule.component.css']
})
export class TeacherScheduleComponent implements OnInit {
    currentAccount: any;
    currentTeacher: TeacherMySuffix;
    teachers: TeacherMySuffix[];
    allSchedules: ScheduleMySuffix[];
    filteredSchedules: ScheduleMySuffix[];
    selectedID: string;
    selectedDate: string;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private teacherScheduleService: TeacherScheduleService,
        private alertService: AlertService,
        private principal: Principal,
    ) {
        this.jhiLanguageService.setLocations([]);
        this.teachers = [];
        this.allSchedules = [];
        this.filteredSchedules = [];
        this.selectedID = '1';
        this.selectedDate = Date.now().toString();
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.loadCurrentTeacher();
    }

    loadCurrentTeacher(){
        this.teacherScheduleService.getCurrentTeacher().subscribe(
            (res: Response) => {
                this.currentTeacher = res.json();
                this.loadTeachers(this.currentTeacher.schoolId);
                this.loadSchedule(this.currentTeacher.schoolId);
            },
            (res: Response) => this.onError(res.json())
        );
    }

    loadTeachers(schoolId: number) {
        this.teacherScheduleService.getTeachersBySchoolId(schoolId).subscribe(
            (res: Response) => {
                this.teachers = res.json();
            });
    }

    loadSchedule(schoolId: number) {
        this.teacherScheduleService.getSchedulesBySchoolId(schoolId).subscribe(
            (res: Response) => {
                this.allSchedules = res.json();
                this.onFormChange();
            },
            (res: Response) => {
                this.onError(res.json());
            }
        );
    }

    onFormChange() {
        this.filteredSchedules = this.teacherScheduleService.filterSchedule(parseInt(this.selectedID, 10), new Date(this.selectedDate.toString()), this.allSchedules);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

}
