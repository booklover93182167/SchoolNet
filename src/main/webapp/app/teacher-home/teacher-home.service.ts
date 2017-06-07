/**
 * Created by slavkosoltys on 28.05.17.
 */
import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import {DateUtils} from 'ng-jhipster';
import {ScheduleMySuffix} from '../entities/schedule/schedule-my-suffix.model';
import {isUndefined} from 'util';

@Injectable()
export class TeacherHomeService {

    private resourceUrlLesson = 'api/teacher-home/lessons/teacher';
    private resourceUrlForm = 'api/teacher-home/forms/teacher';
    private resourceUrlCurrentTeacher = 'api/teacher-home/teachers/current';
    private resourceUrlSchedule = 'api/teacher-home/schedules';

    constructor(private http: Http, private dateUtils: DateUtils) {
    }

    queryLessons(teacherId: number): Observable<Response> {
        return this.http.get(`${this.resourceUrlLesson}/${teacherId}`);
    }

    queryForm(teacherId: number): Observable<Response> {
        return this.http.get(`${this.resourceUrlForm}/${teacherId}`);
    }

    getCurrentTeacher(): Observable<Response> {
        return this.http.get(`${this.resourceUrlCurrentTeacher}`);
    }

    querySchedule(teacherId: number): Observable<Response> {
        return this.http.get(`${this.resourceUrlSchedule}/teacher/${teacherId}`)
            .map((res: any) => this.convertResponse(res));
    }

    updateHomework(schedule: ScheduleMySuffix): Observable<ScheduleMySuffix> {
        const copy: ScheduleMySuffix = Object.assign({}, schedule);
        copy.date = this.dateUtils.toDate(schedule.date);
        return this.http.put(`${this.resourceUrlSchedule}/update`, copy).map((res: Response) => {
            return res.json();
        });
    }

    findSchedule(id: number): Observable<ScheduleMySuffix> {
        return this.http.get(`${this.resourceUrlSchedule}/find/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            jsonResponse.date = this.dateUtils
                .convertDateTimeFromServer(jsonResponse.date);
            return jsonResponse;
        });
    }

    private convertResponse(res: any): any {
        const jsonResponse = res.json();
        for (let i = 0; i < jsonResponse.length; i++) {
            jsonResponse[i].date = this.dateUtils
                .convertDateTimeFromServer(jsonResponse[i].date);
        }
        res._body = jsonResponse;
        return res;
    }

    // private compareDateWithScheduleDate(selecteDate: Date, schedule: ScheduleMySuffix[]): boolean {
    //     if (selecteDate.getDate() === schedule.date.getDate() &&
    //         selecteDate.getFullYear() === schedule.date.getFullYear() &&
    //         selecteDate.getMonth() === schedule.date.getMonth()) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

    filterSchedule(lessonId: number, formId: number, schedules: ScheduleMySuffix[], date: Date): ScheduleMySuffix[] {
        let filteredSchedules: ScheduleMySuffix[] = [];
        if (!isNaN(lessonId) && !isNaN(formId) && !isUndefined(date)) {
            for (let schedule of schedules) {
                if (schedule.lessonId === lessonId && schedule.formId === formId) {
                    if (date.getDate() === schedule.date.getDate() &&
                        date.getFullYear() === schedule.date.getFullYear() &&
                        date.getMonth() === schedule.date.getMonth()) {
                        filteredSchedules.push(schedule);
                    }
                }
            }
        } else if (isNaN(lessonId) && isNaN(formId) && !isUndefined(date)) {
            for (let schedule of schedules) {
                if (date.getDate() === schedule.date.getDate() &&
                    date.getFullYear() === schedule.date.getFullYear() &&
                    date.getMonth() === schedule.date.getMonth()) {
                    filteredSchedules.push(schedule);
                }
            }
        } else if (isNaN(lessonId) && !isNaN(formId) && isUndefined(date)) {
            for (let schedule of schedules) {
                if (schedule.formId === formId) {
                    filteredSchedules.push(schedule);
                }
            }
        } else if (!isNaN(lessonId) && isNaN(formId) && isUndefined(date)) {
            for (let schedule of schedules) {
                if (schedule.lessonId === lessonId) {
                    filteredSchedules.push(schedule);
                }
            }
        } else if (isNaN(lessonId) && !isNaN(formId) && !isUndefined(date)) {
            for (let schedule of schedules) {
                if (schedule.formId === formId) {
                    if (date.getDate() === schedule.date.getDate() &&
                        date.getFullYear() === schedule.date.getFullYear() &&
                        date.getMonth() === schedule.date.getMonth()) {
                        filteredSchedules.push(schedule);
                    }
                }
            }
        } else if (!isNaN(lessonId) && isNaN(formId) && !isUndefined(date)) {
            for (let schedule of schedules) {
                if (schedule.lessonId === lessonId) {
                    if (date.getDate() === schedule.date.getDate() &&
                        date.getFullYear() === schedule.date.getFullYear() &&
                        date.getMonth() === schedule.date.getMonth()) {
                        filteredSchedules.push(schedule);
                    }
                }
            }
        } else if (!isNaN(lessonId) && !isNaN(formId) && isUndefined(date)) {
            for (let schedule of schedules) {
                if (schedule.lessonId === lessonId && schedule.formId === formId) {
                    filteredSchedules.push(schedule);
                }
            }
        }
        return filteredSchedules;
    }
}
