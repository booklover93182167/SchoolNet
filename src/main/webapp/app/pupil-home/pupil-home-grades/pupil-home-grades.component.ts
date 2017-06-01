/**
 * Created by inva on 31-May-17.
 */
import {Component, OnInit, OnDestroy} from '@angular/core';
import { Response } from '@angular/http';
import {EventManager, JhiLanguageService, AlertService} from 'ng-jhipster';
import {AttendancesMySuffix} from '../../entities/attendances/attendances-my-suffix.model';
import {Subscription} from "rxjs/Rx";
import {LessonMySuffix} from "../../entities/lesson/lesson-my-suffix.model";
import {PupilMySuffix} from "../../entities/pupil/pupil-my-suffix.model";
import {PupilHomeService} from "../pupil-home.service";
import {PupilHomeComponent} from "../pupil-home.component";
import {Principal} from "../../shared/auth/principal.service";

@Component({
    selector: 'pupil-home-grades',
    templateUrl: 'pupil-home-grades.component.html',
    styleUrls: ['pupil-home-grades.component.css'],
})

export class PupilHomeGradesComponent implements OnInit {
    pupilAttendances: AttendancesMySuffix[] = [];
    pupilLessons: LessonMySuffix[] = [];
    account: any;
    eventSubscriber: Subscription;
    currentPupil: PupilMySuffix;

    constructor(
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private principal: Principal,
        private pupilHomeService: PupilHomeService
    ) {
        this.jhiLanguageService.setLocations(['home']);
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
        // this.currentPupil = this.pupilHomeComponent.currentPupil;
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

}
