/**
 * Created by Kolja on 22.05.2017.
 */
import { Injectable } from '@angular/core';
import {Subject, Observable} from "rxjs";

@Injectable()
export class UserHomeService{


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
