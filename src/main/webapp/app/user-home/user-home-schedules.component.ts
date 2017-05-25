import { Component, OnInit, OnDestroy } from '@angular/core';
import { Response } from '@angular/http';
import { Subscription } from 'rxjs/Rx';
import { EventManager, JhiLanguageService, AlertService } from 'ng-jhipster';

import { UserHomeSchedules } from './user-home-schedules.model';
import { UserHomeService } from './user-home.service';
import { ITEMS_PER_PAGE, Principal } from '../shared';
//service to retrieve schedules for pupil
@Component({
    selector: 'jhi-user-home-schedules',
    templateUrl: './user-home-schedules.component.html'
})
export class UserHomeSchedulesComponent implements OnInit {
    userSchedules: UserHomeSchedules[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private userHomeService: UserHomeService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private principal: Principal
    ) {
        this.jhiLanguageService.setLocations(['schedule']);
    }

    ngOnInit(){
        this.loadByFormIdAndMonth();
    }
    //function to load form by ID and Month, with hardcoded formId 1 and month June
    // TODO: add year param and tether with UI event
    loadByFormIdAndMonth() {
        this.userHomeService.findByFormAndMonth(1, 6).subscribe(
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
}
