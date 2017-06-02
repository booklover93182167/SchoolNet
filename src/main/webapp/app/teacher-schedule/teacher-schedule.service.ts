import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { DateUtils } from 'ng-jhipster';

import { ScheduleMySuffix } from './../entities/schedule/schedule-my-suffix.model';

@Injectable()
export class TeacherScheduleService {

    private resourceUrlSchool = 'api/teacher-schedule/school';
    private resourceUrlSchedule = 'api/teacher-schedule/schedule';
    private resourceUrlCurrentTeacher = 'api/teacherhome/current';

    constructor(private http: Http, private dateUtils: DateUtils) { }

    getCurrentTeacher(): Observable<Response> {
        return this.http.get(`${this.resourceUrlCurrentTeacher}`);
    }

    getTeachersBySchoolId(schoolId: number): Observable<Response> {
        return this.http.get(`${this.resourceUrlSchool}/${schoolId}`);
    }

    getSchedulesBySchoolId(schoolId: number): Observable<Response> {
        return this.http.get(`${this.resourceUrlSchedule}/${schoolId}`)
            .map((res: any) => this.convertResponse(res));
    }

    filterSchedule(teacherID: number, date: Date, teacherSchedule: ScheduleMySuffix[]): ScheduleMySuffix[] {
        let schedulesForDate: ScheduleMySuffix[] = [];
        for (let schedule of teacherSchedule) {
            if (date.getDate() === schedule.date.getDate() &&
                date.getMonth() === schedule.date.getMonth() &&
                date.getFullYear() === schedule.date.getFullYear() &&
                teacherID === schedule.teacherId) {
                schedulesForDate.push(schedule);
            }
        }
        return schedulesForDate;
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
