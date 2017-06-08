/**
 * Created by inva on 31-May-17.
 */
import {Component, OnInit, OnDestroy, Input, Inject} from '@angular/core';
import { Response } from '@angular/http';
import { EventManager, JhiLanguageService, AlertService } from 'ng-jhipster';
import { AttendancesMySuffix } from '../../entities/attendances/attendances-my-suffix.model';
import { Subscription } from 'rxjs/Rx';
import { LessonMySuffix } from '../../entities/lesson/lesson-my-suffix.model';
import { PupilMySuffix } from '../../entities/pupil/pupil-my-suffix.model';
import { PupilHomeService } from '../pupil-home.service';
import { PupilHomeComponent } from '../pupil-home.component';
import { Principal } from '../../shared/auth/principal.service';

@Component({
    selector: 'jhi-pupil-home-grades',
    templateUrl: 'pupil-home-grades.component.html',
    styleUrls: ['pupil-home-grades.component.css'],
})

export class PupilHomeGradesComponent {
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
        this.jhiLanguageService.setLocations(['pupil-home-calendar']);
    }

    ngOnInit() {
        if(!this.pupilHomeService.currentPupilExist()) {
            this.loadCurrentPupil();
        } else {
            this.currentPupil = this.pupilHomeService.getPupil();
            this.loadDistinctLessons(this.currentPupil.formId);
        }
    }

    //load all distinct lessons into pupilLessons
    loadDistinctLessons(formId: number) {
        this.pupilHomeService.getDistinctLessons(formId).subscribe(
            (res: Response) => {
                this.pupilLessons = res.json();
                return true;
            },
        );
        return false;
    }

    //load all distinct lessons into pupilLessons
    findAllByPupilAndLessonId(pupilId: number, lessonId: number){
        console.log('req to get all attendances for pupil '+pupilId + ' and lessonId '+lessonId);
        this.pupilHomeService.findAllByPupilAndLessonId(pupilId, lessonId).subscribe(
            (res: Response) => {
                this.pupilAttendances = res.json();
            },
        );
    }

    loadCurrentPupil() {
        this.pupilHomeService.loadCurrentPupil().subscribe(
            (res: Response) => {
                this.currentPupil = res.json();
                this.pupilHomeService.setPupil(this.currentPupil);
                this.loadDistinctLessons(this.currentPupil.formId);
            },
            (res: Response) => this.onError(res.json())
        );
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    getAverageGrade(): number {
        let averageGrade = 0;
        let sum = 0;
        let count = 0;
        for(let i = 0; i < this.pupilAttendances.length; i++) {
            if(this.pupilAttendances[i].grade && this.pupilAttendances[i].grade !== 0) {
                sum += this.pupilAttendances[i].grade;
                count++;
            }
        }
        averageGrade = sum / count;
        return averageGrade;
    }

}
