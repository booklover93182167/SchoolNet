import {Component, OnInit, OnDestroy, Input, OnChanges, ChangeDetectionStrategy} from '@angular/core';
import {Response} from '@angular/http';
import {Subscription} from 'rxjs/Rx';

import {PupilHomeSchedules} from './pupil-home-schedules.model';
import {PupilHomeService} from '../pupil-home.service';
import {ITEMS_PER_PAGE, Principal} from '../../shared';
import {EventManager, JhiLanguageService, AlertService} from 'ng-jhipster';
import {PupilHomeComponent} from '../pupil-home.component';
// service to retrieve schedules for pupil
@Component({
    selector: 'jhi-pupil-home-schedules',
    templateUrl: './pupil-home-schedules.component.html',
})
export class PupilHomeSchedulesComponent implements OnInit {
    pupilSchedules: PupilHomeSchedules[] = [];
    account: any;
    eventSubscriber: Subscription;
    currentSchedules: PupilHomeSchedules[] = [];
    schedulesWithBlanks: PupilHomeSchedules[] = [];

    selectedDate: Date = new Date(Date.now());

    // values to show selected homework
    selectedHomework: string = null;
    isSelectedHomework = false;

    selectHomework(homework: string): void {
        if (this.isSelectedHomework) {
            this.selectedHomework = null;
            this.isSelectedHomework = false;
        }else {
            this.selectedHomework = homework;
            this.isSelectedHomework = true;
        }
    }

    constructor(private pupilHomeService: PupilHomeService,
                private alertService: AlertService,
                private eventManager: EventManager,
                private principal: Principal,
                private pupilHomeComponent: PupilHomeComponent,
                private jhiLanguageService: JhiLanguageService) {
        this.jhiLanguageService.setLocations(['pupil-home-calendar']);
        // subscribe on changes in calendar
        this.pupilHomeService.dateToSend$.subscribe(
            (data) => {
                this.selectedDate = data;
                // update schedules when new date is selected
                this.schedulesWithBlanks = this.getSchedulesWithBlanks();
            });
    }

    ngOnInit() {
        this.loadByFormId();
    }

    // function to load schedules for form for current user(if he is pupil)
    loadByFormId() {
        this.pupilHomeService.findByFormId(this.pupilHomeService.getPupil().formId).subscribe(
            (res: Response) => {
                this.pupilSchedules = res.json();
                // initialize schedules for today
                this.currentSchedules = this.pupilHomeService.getSchedulesForDate(this.selectedDate, this.pupilSchedules);
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

    // get an array of schedules fom pupilSchedules for selectedDate
    getSchedules(): PupilHomeSchedules[] {
        return this.generateArray(this.pupilHomeService.getSchedulesForDate(this.selectedDate, this.pupilSchedules));
    }

    // helper method to make new array iterable
    generateArray(obj) {
        return Object.keys(obj).map((key) => obj[key] );
    }

    // generate schedules table with blank fields(where there is no lesson at this position)
    getSchedulesWithBlanks(): PupilHomeSchedules[] {
        this.schedulesWithBlanks = [];
        let currentSched = this.getSchedules();
        for (let i = 1; i < 11; i++) {
            let match = false;
            for (let j = 0; j < currentSched.length; j++) {
                if (currentSched[j].lessonPosition === i) {
                    this.schedulesWithBlanks.push(currentSched[j]);
                    match = true;
                }
            }
            if (match === false) {
                let blankSchedule = new PupilHomeSchedules(null, null, '', i, true, null, null, null, null, null, null, null, null, null, null);
                this.schedulesWithBlanks.push(blankSchedule);
            }
        }
        return this.schedulesWithBlanks;
    }

}
