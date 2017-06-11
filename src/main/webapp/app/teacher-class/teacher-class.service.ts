import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import {DateUtils} from 'ng-jhipster';
import {ScheduleMySuffix} from '../entities/schedule/schedule-my-suffix.model';

@Injectable()
export class TeacherClassService {

    private resourceUrlForm = 'api/teacher-class/form';
    private resourceUrlCurrentTeacher = 'api/teacher-home/teachers/current';

    constructor(private http: Http, private dateUtils: DateUtils) {
    }



    queryForm(teacherId: number): Observable<Response> {
        return this.http.get(`${this.resourceUrlForm}/${teacherId}`);
    }

    getCurrentTeacher(): Observable<Response> {
        return this.http.get(`${this.resourceUrlCurrentTeacher}`);
    }



    private convertResponse(res: any): any {
        const jsonResponse = res.json();

        res.body = jsonResponse;
        return res;
    }


}
