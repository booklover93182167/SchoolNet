import {Component, OnInit, OnDestroy, Input, OnChanges, ChangeDetectionStrategy} from '@angular/core';
import {Response} from '@angular/http';
import {Subscription} from 'rxjs/Rx';
import {EventManager, JhiLanguageService, AlertService} from 'ng-jhipster';

import {PupilHomeSchedules} from './pupil-home-schedules.model';
import {PupilHomeService} from '../pupil-home.service';
import {ITEMS_PER_PAGE, Principal} from '../../shared';
// service to retrieve schedules for pupil
@Component({
    selector: 'jhi-pupil-home-schedules',
    templateUrl: './pupil-home-schedules.component.html',
})
export class PupilHomeSchedulesComponent implements OnInit {
    pupilSchedules: PupilHomeSchedules[] = [];
    currentAccount: any;
    eventSubscriber: Subscription;

    selectedDate: Date;

    constructor(private jhiLanguageService: JhiLanguageService,
                private pupilHomeService: PupilHomeService,
                private alertService: AlertService,
                private eventManager: EventManager,
                private principal: Principal) {
        // subscribe on changes in calendar
        this.pupilHomeService.dateToSend$.subscribe(
            (data) => {
                this.selectedDate = data;
            });
        this.jhiLanguageService.setLocations(['home']);

    }

    ngOnInit() {
        this.loadByFormId();
        this.selectedDate = new Date(Date.now());
    }

    // function to load schedules for form for current user(if he is pupil)
    loadByFormId() {
        this.pupilHomeService.findByForm().subscribe(
            (res: Response) => {
                this.pupilSchedules = res.json();
            },
            (res: Response) => this.onError(res.json())
        );

    }

    trackId(index: number, item: PupilHomeSchedules) {
        return item.id;
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    checkDate(date: Date): boolean {
        if (date.getDate() === this.selectedDate.getDate() &&
            date.getFullYear() === this.selectedDate.getFullYear() &&
            date.getMonth() === this.selectedDate.getMonth()) {
            console.log('profit');
            return true;
        }
    }

}
