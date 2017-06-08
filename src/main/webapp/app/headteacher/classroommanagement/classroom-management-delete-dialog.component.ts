import {Component, OnInit, OnDestroy} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {NgbActiveModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {EventManager, JhiLanguageService} from 'ng-jhipster';

import {ClassroomManagement} from './classroom-management.model';
import {ClassroomManagementPopupService} from './classroom-management-popup.service';
import {ClassroomManagementService} from './classroom-management.service';

@Component({
    selector: 'jhi-classroom-management-delete-dialog',
    templateUrl: './classroom-management-delete-dialog.component.html'
})
export class ClassroomManagementDeleteDialogComponent {

    classroom: ClassroomManagement;

    constructor(private jhiLanguageService: JhiLanguageService,
                private classroomService: ClassroomManagementService,
                public activeModal: NgbActiveModal,
                private eventManager: EventManager) {
        this.jhiLanguageService.setLocations(['classroom']);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.classroomService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'classroomListModification',
                content: 'Deleted an classroom'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-classroom-management-delete-popup',
    template: ''
})
export class ClassroomManagementDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(private route: ActivatedRoute,
                private classroomPopupService: ClassroomManagementPopupService) {
    }

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.modalRef = this.classroomPopupService
                .open(ClassroomManagementDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
