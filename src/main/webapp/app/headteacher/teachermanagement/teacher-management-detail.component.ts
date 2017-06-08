import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager , JhiLanguageService  } from 'ng-jhipster';

import { TeacherManagement } from './teacher-management.model';
import { TeacherManagementService } from './teacher-management.service';

@Component({
    selector: 'jhi-teacher-detail',
    templateUrl: './teacher-management-detail.component.html'
})
export class TeacherManagementDetailComponent implements OnInit, OnDestroy {

    teacher: TeacherManagement;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private jhiLanguageService: JhiLanguageService,
        private teacherService: TeacherManagementService,
        private route: ActivatedRoute
    ) {
        this.jhiLanguageService.setLocations(['teacher']);
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTeachers();
    }

    load(id) {
        this.teacherService.find(id).subscribe((teacher) => {
            this.teacher = teacher;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTeachers() {
        this.eventSubscriber = this.eventManager.subscribe('teacherListModification', (response) => this.load(this.teacher.id));
    }
}
