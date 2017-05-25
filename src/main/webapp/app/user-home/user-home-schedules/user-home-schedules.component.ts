import {Component, OnInit, OnDestroy, Input, OnChanges, ChangeDetectionStrategy} from '@angular/core';
import {Response} from '@angular/http';
import {Subscription} from 'rxjs/Rx';
import {EventManager, JhiLanguageService, AlertService} from 'ng-jhipster';

import {UserHomeSchedules} from './user-home-schedules.model';
import {UserHomeService} from '../user-home.service';
import {ITEMS_PER_PAGE, Principal} from '../../shared';
//service to retrieve schedules for pupil
@Component({
    selector: 'jhi-user-home-schedules',
    templateUrl: './user-home-schedules.component.html',
})
export class UserHomeSchedulesComponent implements OnInit{
    userSchedules: UserHomeSchedules[] = [];
    currentAccount: any;
    eventSubscriber: Subscription;

    selectedDayArray: UserHomeSchedules[];

    selectedDate: Date;

    constructor(private jhiLanguageService: JhiLanguageService,
                private userHomeService: UserHomeService,
                private alertService: AlertService,
                private eventManager: EventManager,
                private principal: Principal) {
        //subscribe on chsnges in calendar
        this.userHomeService.dateToSend$.subscribe(
            data => {
                this.selectedDate = data;
            });
        this.jhiLanguageService.setLocations(['home']);

    }

    ngOnInit() {
        this.loadByFormIdAndMonth();
        this.selectedDate = new Date(Date.now());
    }

    //function to load form by ID
    // TODO: add year param and tether with UI event
    loadByFormIdAndMonth() {
        this.userHomeService.findByFormAndMonth(1).subscribe(
            (res: Response) => {
                this.userSchedules = res.json();
            },
            (res: Response) => this.onError(res.json())
        );

    }

    trackId(index: number, item: UserHomeSchedules) {
        return item.id;
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    checkDate(date: Date): boolean{
        if(date.getDate()==this.selectedDate.getDate()&&
            date.getFullYear()==this.selectedDate.getFullYear()&&
            date.getMonth()==this.selectedDate.getMonth()){
        console.log("profit");
            return true;
        }
    }

}
