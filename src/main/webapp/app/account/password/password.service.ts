import { Injectable } from '@angular/core';
import {Http, Response} from '@angular/http';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class Password {

    private resourceUrl = 'api/account/change_password';

    constructor(private http: Http) {}

    save(newPassword: string, currentPassword: string): Observable<any> {
        return this.http.post(this.resourceUrl, {newPassword, currentPassword});
    }

}
