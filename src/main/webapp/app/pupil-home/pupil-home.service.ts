/**
 * Created by Kolja on 22.05.2017.
 */
import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { Http, Response, URLSearchParams, BaseRequestOptions } from '@angular/http';
import { PupilHomeSchedules } from './pupil-home-schedules/pupil-home-schedules.model';
import { DateUtils, AlertService } from 'ng-jhipster';
import { PupilMySuffix } from '../entities/pupil/pupil-my-suffix.model';

@Injectable()
export class PupilHomeService {

    private resourceUrl = 'api/pupilhome';
    private resourceLessonUrl = 'api/pupilhome/distinctform';
    currentPupil: PupilMySuffix;
    // Observable string sources
    private dateToSend = new Subject<Date>();

    // Observable string streams
    dateToSend$ = this.dateToSend.asObservable();

    constructor(private http: Http, private dateUtils: DateUtils,
    private alertService: AlertService) { }

    findByFormId(formId: number): Observable<Response> {
        return this.http.get(`${this.resourceUrl}/getschedules/${formId}`).map((res: Response) =>
            this.convertResponse(res));
    }

    findAllByPupilAndLessonId(pupilId: number, lessonId: number): Observable<Response> {
        return this.http.get(`${this.resourceUrl}/${pupilId}/${lessonId}`).map((res: Response) =>
            this.convertResponse(res));
    }

    //get all distinct lessons for this form
    getDistinctLessons(formId: number): Observable<Response> {
        console.log("get all lessons");
        return this.http.get(`${this.resourceLessonUrl}/${formId}`).map((res: Response) =>
            this.convertResponse(res));
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

    loadCurrentPupil(): Observable<Response> {
        return this.http.get(`${this.resourceUrl}/getpupil`);
    }

    currentPupilExist(): boolean {
        if(this.currentPupil) {
            return true;
        } else {
            return false;
        }
    }

    // Service message commands
    publishData(data: Date) {
        this.dateToSend.next(data);
    }

    setPupil(pupil: PupilMySuffix) {
        this.currentPupil = pupil;
    }

    getPupil() {
        return this.currentPupil;
    }

    // get schedules array for specified date
    getSchedulesForDate(date: Date, pupilSchedules: PupilHomeSchedules[]): PupilHomeSchedules[] {
        let schedulesForDate: PupilHomeSchedules[] = [];
        for(let schedule of pupilSchedules){
            if (date.getDate() === schedule.date.getDate() &&
                date.getFullYear() === schedule.date.getFullYear() &&
                date.getMonth() === schedule.date.getMonth()) {
                schedulesForDate.push(schedule);
            }
        }
        return schedulesForDate;
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

}
