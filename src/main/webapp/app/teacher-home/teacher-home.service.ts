/**
 * Created by slavkosoltys on 28.05.17.
 */
import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import {DateUtils} from 'ng-jhipster';
import {ScheduleMySuffix} from "../entities/schedule/schedule-my-suffix.model";

@Injectable()
export class TeacherHomeService {

    private resourceUrlLesson = 'api/teacher-home/lessons/teacher';
    private resourceUrlForm = 'api/teacher-home/forms/teacher';
    private resourceUrlCurrentTeacher = 'api/teacher-home/teachers/current';
    private resourceUrlSchedule = 'api/teacher-home/schedules/teacher';
    private resourceUrlForTeacherUpdateSchedule = 'api/teacher-home/schedules/update';
    private resourceUrlForTeacherFindSchedule = 'api/teacher-home/schedules/find';


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
        return this.http.get(`${this.resourceUrlSchedule}/${teacherId}`)
            .map((res: any) => this.convertResponse(res));
    }

    updateHomework(schedule: ScheduleMySuffix): Observable<ScheduleMySuffix> {
        const copy: ScheduleMySuffix = Object.assign({}, schedule);

        copy.date = this.dateUtils.toDate(schedule.date);
        return this.http.put(this.resourceUrlForTeacherUpdateSchedule, copy).map((res: Response) => {
            return res.json();
        });
    }

    findSchedule(id: number): Observable<ScheduleMySuffix> {
        return this.http.get(`${this.resourceUrlForTeacherFindSchedule}/${id}`).map((res: Response) => {
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
}
