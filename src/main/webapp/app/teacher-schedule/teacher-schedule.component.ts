import { Component, OnInit } from '@angular/core';
import { Response } from '@angular/http';
import { JhiLanguageService, AlertService } from 'ng-jhipster';

import { ScheduleMySuffix } from './../entities/schedule/';
import { TeacherMySuffix } from '../entities/teacher/teacher-my-suffix.model';

import { TeacherScheduleService } from './teacher-schedule.service';

@Component({
    selector: 'jhi-teacher-schedule',
    templateUrl: './teacher-schedule.component.html',
    styleUrls: ['./teacher-schedule.component.css']
})
export class TeacherScheduleComponent implements OnInit {
    currentTeacherAccount: TeacherMySuffix;
    teachers: TeacherMySuffix[];
    allSchedules: ScheduleMySuffix[];
    filteredSchedules: ScheduleMySuffix[];
    selectedID: string;
    selectedDate: string;
    selectedPeriod: string;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private teacherScheduleService: TeacherScheduleService,
        private alertService: AlertService,
    ) {
        this.jhiLanguageService.setLocations([]);
        this.teachers = [];
        this.allSchedules = [];
        this.filteredSchedules = [];
    }

    ngOnInit() {
        this.loadCurrentTeacher();
    }

    loadCurrentTeacher() {
        this.teacherScheduleService.getCurrentTeacher().subscribe(
            (res: Response) => {
                this.currentTeacherAccount = res.json();
                this.loadTeachers(this.currentTeacherAccount.schoolId);
                this.loadSchedule(this.currentTeacherAccount.schoolId);
                this.selectedID = String(this.currentTeacherAccount.id);
                this.selectedDate = new Date(Date.now()).toISOString().substring(0, 10);
                this.selectedPeriod = 'day';

                this.selectedID = '1'; // delete
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
                this.filterSchedule();
            },
            (res: Response) => {
                this.onError(res.json());
            }
        );
    }

    filterSchedule() {
        this.filteredSchedules = this.teacherScheduleService.filterSchedule(parseInt(this.selectedID, 10), new Date(this.selectedDate.toString()), this.allSchedules);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

}
