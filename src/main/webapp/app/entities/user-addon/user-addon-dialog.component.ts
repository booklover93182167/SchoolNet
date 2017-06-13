import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService, DataUtils } from 'ng-jhipster';

import { UserAddon } from './user-addon.model';
import { UserAddonPopupService } from './user-addon-popup.service';
import { UserAddonService } from './user-addon.service';
import { User, UserService } from '../../shared';

@Component({
    selector: 'jhi-user-addon-dialog',
    templateUrl: './user-addon-dialog.component.html'
})
export class UserAddonDialogComponent implements OnInit {

    userAddon: UserAddon;
    authorities: any[];
    isSaving: boolean;

    users: User[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: DataUtils,
        private alertService: AlertService,
        private userAddonService: UserAddonService,
        private userService: UserService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.userService.query().subscribe(
            (res: Response) => { this.users = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, userAddon, field, isImage) {
        if (event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            if (isImage && !/^image\//.test(file.type)) {
                return;
            }
            this.dataUtils.toBase64(file, (base64Data) => {
                userAddon[field] = base64Data;
                userAddon[`${field}ContentType`] = file.type;
            });
        }
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.userAddon.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userAddonService.update(this.userAddon));
        } else {
            this.subscribeToSaveResponse(
                this.userAddonService.create(this.userAddon));
        }
    }

    private subscribeToSaveResponse(result: Observable<UserAddon>) {
        result.subscribe((res: UserAddon) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: UserAddon) {
        this.eventManager.broadcast({ name: 'userAddonListModification', content: 'OK'});
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

    trackUserById(index: number, item: User) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-user-addon-popup',
    template: ''
})
export class UserAddonPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userAddonPopupService: UserAddonPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.userAddonPopupService
                    .open(UserAddonDialogComponent, params['id']);
            } else {
                this.modalRef = this.userAddonPopupService
                    .open(UserAddonDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
