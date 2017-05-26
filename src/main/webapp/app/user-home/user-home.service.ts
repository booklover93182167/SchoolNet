/**
 * Created by Kolja on 22.05.2017.
 */
import { Injectable } from '@angular/core';
import {Subject, Observable} from "rxjs";
import { Http, Response, URLSearchParams, BaseRequestOptions } from '@angular/http';
import { UserHomeSchedules } from './user-home-schedules/user-home-schedules.model';
import { DateUtils } from 'ng-jhipster';
@Injectable()
export class UserHomeService {

    private resourceUrl = 'api/userhome';

    constructor(private http: Http, private dateUtils: DateUtils) { }

    findByFormAndMonth(): Observable<Response> {
        return this.http.get(`${this.resourceUrl}/getschedules`).map((res: Response) =>
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

    ///////////////////////////////////////////////////////////
    // Observable string sources
    private dateToSend = new Subject<Date>();

    // Observable string streams
    dateToSend$ = this.dateToSend.asObservable();

    // Service message commands
    publishData(data: Date) {
        this.dateToSend.next(data);
    }
    /////////////////////////////////////////////////////////////////
}
