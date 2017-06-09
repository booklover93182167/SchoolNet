import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { DateUtils } from 'ng-jhipster';

import { ScheduleMySuffix } from './../entities/schedule/schedule-my-suffix.model';

@Injectable()
export class TeacherScheduleService {

    private resourceUrlSchedule = 'api/teacher-schedule/schedule';
    private resourceUrlAllTeachers = 'api/teacher-schedule/teachers/all/';
    private resourceUrlCurrentTeacher = 'api/teacher-schedule/teachers/current';

    constructor(private http: Http, private dateUtils: DateUtils) { }

    getCurrentTeacher(): Observable<Response> {
        return this.http.get(`${this.resourceUrlCurrentTeacher}`);
    }

    getAllTeachersByCurrentTeacher(): Observable<Response> {
        return this.http.get(`${this.resourceUrlAllTeachers}`);
    }

    getSchedulesBySchoolId(schoolId: number): Observable<Response> {
        return this.http.get(`${this.resourceUrlSchedule}/${schoolId}`)
            .map((res: any) => this.convertResponse(res));
    }

    filterSchedule(teacherID: number, date: Date, teacherSchedule: ScheduleMySuffix[]): ScheduleMySuffix[] {
        const schedulesForDate: ScheduleMySuffix[] = [];
        for (const schedule of teacherSchedule) {
            if (date.getDate() === schedule.date.getDate() &&
                date.getMonth() === schedule.date.getMonth() &&
                date.getFullYear() === schedule.date.getFullYear() &&
                teacherID === schedule.teacherId) {
                schedulesForDate.push(schedule);
            }
        }
        return schedulesForDate;
    }

    filterWeekSchedule(teacherID: number, date: Date, teacherSchedule: ScheduleMySuffix[]): ScheduleMySuffix[] {
        let firstDayOfWeek = this.getMonday(date);
        let lastDayOfWeek = this.addDays(firstDayOfWeek, 6);

        const schedulesForDate: ScheduleMySuffix[] = [];
        for (const schedule of teacherSchedule) {
            if (firstDayOfWeek < schedule.date && schedule.date < lastDayOfWeek &&
                teacherID === schedule.teacherId) {
                schedulesForDate.push(schedule);
            }
        }
        return schedulesForDate;
    }

    getMonday(date: Date): Date {
        let day = date.getDay();
        let diff = date.getDate() - day + (day == 0 ? -6 : 1); // adjust when day is sunday
        return new Date(date.setDate(diff));
    }

    addDays(date: Date, days: number): Date {
        let result = new Date(date);
        result.setDate(result.getDate() + days);
        return result;
    }

    private convertResponse(res: any): any {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            jsonResponse[i].date = this.dateUtils.convertDateTimeFromServer(jsonResponse[i].date);
        }
        res._body = jsonResponse;
        return res;
    }

}
