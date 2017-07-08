import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, JhiLanguageService } from 'ng-jhipster';

import { Course } from './course.model';
import { CoursePopupService } from './course-popup.service';
import { CourseService } from './course.service';
import { FormMySuffix, FormMySuffixService } from '../form';
import { LessonMySuffix, LessonMySuffixService } from '../lesson';
import { TeacherMySuffix, TeacherMySuffixService } from '../teacher';

@Component({
    selector: 'jhi-course-dialog',
    templateUrl: './course-dialog.component.html'
})
export class CourseDialogComponent implements OnInit {

    course: Course;
    authorities: any[];
    isSaving: boolean;

    forms: FormMySuffix[];

    lessons: LessonMySuffix[];

    teachers: TeacherMySuffix[];
    constructor(
        public activeModal: NgbActiveModal,
        private jhiLanguageService: JhiLanguageService,
        private alertService: AlertService,
        private courseService: CourseService,
        private formService: FormMySuffixService,
        private lessonService: LessonMySuffixService,
        private teacherService: TeacherMySuffixService,
        private eventManager: EventManager
    ) {
        this.jhiLanguageService.setLocations(['course']);
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.formService.query().subscribe(
            (res: Response) => { this.forms = res.json(); }, (res: Response) => this.onError(res.json()));
        this.lessonService.query().subscribe(
            (res: Response) => { this.lessons = res.json(); }, (res: Response) => this.onError(res.json()));
        this.teacherService.query().subscribe(
            (res: Response) => { this.teachers = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.course.id !== undefined) {
            this.courseService.update(this.course)
                .subscribe((res: Course) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.courseService.create(this.course)
                .subscribe((res: Course) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Course) {
        this.eventManager.broadcast({ name: 'courseListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackFormById(index: number, item: FormMySuffix) {
        return item.id;
    }

    trackLessonById(index: number, item: LessonMySuffix) {
        return item.id;
    }

    trackTeacherById(index: number, item: TeacherMySuffix) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-course-popup',
    template: ''
})
export class CoursePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private coursePopupService: CoursePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.coursePopupService
                    .open(CourseDialogComponent, params['id']);
            } else {
                this.modalRef = this.coursePopupService
                    .open(CourseDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
