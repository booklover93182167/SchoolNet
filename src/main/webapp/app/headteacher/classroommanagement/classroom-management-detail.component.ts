import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs/Rx';
import {EventManager, JhiLanguageService} from 'ng-jhipster';

import {ClassroomManagement} from './classroom-management.model';
import {ClassroomManagementService} from './classroom-management.service';

@Component({
    selector: 'jhi-classroom-management-detail',
    templateUrl: './classroom-management-detail.component.html'
})
export class ClassroomManagementDetailComponent implements OnInit, OnDestroy {

    classroom: ClassroomManagement;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(private eventManager: EventManager,
                private jhiLanguageService: JhiLanguageService,
                private classroomService: ClassroomManagementService,
                private route: ActivatedRoute) {
        this.jhiLanguageService.setLocations(['classroom']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInClassrooms();
    }

    load(id) {
        this.classroomService.find(id).subscribe((classroom) => {
            this.classroom = classroom;
        });
    }

    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInClassrooms() {
        this.eventSubscriber = this.eventManager.subscribe('classroomListModification', (response) => this.load(this.classroom.id));
    }
}
