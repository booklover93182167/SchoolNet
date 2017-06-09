import { Component, OnInit } from '@angular/core';
import { Response } from '@angular/http';
import { JhiLanguageService, AlertService } from 'ng-jhipster';

import { ScheduleMySuffix } from './../entities/schedule/';
import { TeacherMySuffix } from '../entities/teacher/teacher-my-suffix.model';

import { TeacherScheduleService } from './teacher-schedule.service';

import { INgxMyDpOptions, IMyDateModel } from 'ngx-mydatepicker';
import {teacherScheduleRoute} from "./teacher-schedule.route";

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
    schedulesWithBlanks: ScheduleMySuffix[];
    selectedID: string;
    selectedPeriod: string;
    dateString: string;
    dateObject: Object;
    weekSchedule = [];

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private teacherScheduleService: TeacherScheduleService,
        private alertService: AlertService,
    ) {
        this.jhiLanguageService.setLocations(['teacher-schedule']);
        this.teachers = [];
        this.allSchedules = [];
        this.filteredSchedules = [];
        this.schedulesWithBlanks = [];

        this.selectedPeriod = 'week';
        this.dateString = new Date(Date.now()).toString();
    }

    private datepickerOptions: INgxMyDpOptions = {
        dateFormat: 'dd.mm.yyyy'
    };

    setDate(): void {
        let d = new Date();
        this.dateObject = {
            date: {
                year: d.getFullYear(),
                month: d.getMonth() + 1,
                day: d.getDate()}
        };
    }

    onDateChanged(event: IMyDateModel): void {
        this.dateString = event.jsdate.toString();
        this.filterSchedule();
    }


    filterSchedule() {
        if (this.selectedPeriod == 'day') {
            this.filteredSchedules = this.teacherScheduleService.filterSchedule(parseInt(this.selectedID, 10), new Date(this.dateString), this.allSchedules);
            this.makeScheduleWithBlanks();
        }
        if (this.selectedPeriod == 'week') {
            this.filteredSchedules = this.teacherScheduleService.filterWeekSchedule(parseInt(this.selectedID, 10), new Date(this.dateString), this.allSchedules);
            this.groupSchedulesByDate();
        }
    }

    groupSchedulesByDate() {
        this.weekSchedule = [];

        // console.log(JSON.stringify(this.filteredSchedules, null, 2));

        let tempDate = this.teacherScheduleService.getMonday(new Date(this.dateString));
        for (let i = 1; i <= 7; i++) {
            let item = {};
            item["day"] = tempDate;
            item["schedule"] = [];

            for(let i = 1; i <= 10; i++) {
                let blankSchedule = new ScheduleMySuffix(null, null, '', i, true, null, null, null, null, null, '', null, '', '', '');
                item["schedule"].push(blankSchedule);
            }

            this.weekSchedule.push(item);
            tempDate = this.teacherScheduleService.addDays(tempDate, 1);
        }

        for (let i = 0; i < this.filteredSchedules.length; i++) {
            // console.log('id: ' + this.filteredSchedules[i].id);
            // console.log('date: ' + this.filteredSchedules[i].date.toISOString().substring(0, 10));

            let day = this.filteredSchedules[i].date.getDay() - 1;
            let schedule = this.filteredSchedules[i];
            // let date = this.filteredSchedules[i].date.toISOString().substring(0, 10);
            let lessonPosition = this.filteredSchedules[i].lessonPosition;

            this.weekSchedule[day].schedule[lessonPosition-1] = schedule;
        }
        // console.log(JSON.stringify(this.weekSchedule, null, 4));
    }

    makeScheduleWithBlanks() {
        this.schedulesWithBlanks = [];

        for(let i = 1; i <= 10; i++) {
            let blankSchedule = new ScheduleMySuffix(null, null, '', i, true, null, null, null, null, null, '', null, '', '', '');
            this.schedulesWithBlanks.push(blankSchedule);
        }

        for(let i = 0; i < this.filteredSchedules.length; i++) {
            let lessonPosition = this.filteredSchedules[i].lessonPosition;
            this.schedulesWithBlanks[lessonPosition-1] = this.filteredSchedules[i];
        }

        this.filteredSchedules = this.schedulesWithBlanks;
    }

    ngOnInit() {
        this.setDate();
        this.loadCurrentTeacher();
    }

    loadCurrentTeacher() {
        this.teacherScheduleService.getCurrentTeacher().subscribe(
            (res: Response) => {
                this.currentTeacherAccount = res.json();
                this.loadTeachers();
                this.loadSchedule(this.currentTeacherAccount.schoolId);
                this.selectedID = String(this.currentTeacherAccount.id);
            },
            (res: Response) => this.onError(res.json())
        );
    }

    loadTeachers() {
        this.teacherScheduleService.getAllTeachersByCurrentTeacher().subscribe(
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

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

}
