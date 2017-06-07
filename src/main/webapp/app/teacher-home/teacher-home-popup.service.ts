import {Component, Injectable} from '@angular/core';
import {DatePipe} from '@angular/common';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {ScheduleMySuffix} from '../entities/schedule/schedule-my-suffix.model';
import {TeacherHomeService} from './teacher-home.service';

@Injectable()
export class TeacherHomePopupService {
    private isOpen = false;

    constructor(private datePipe: DatePipe,
                private modalService: NgbModal,
                private router: Router,
                private teacherHomeService: TeacherHomeService) {
    }

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.teacherHomeService.findSchedule(id).subscribe((schedule) => {
                schedule.date = this.datePipe
                    .transform(schedule.date, 'yyyy-MM-ddThh:mm');
                this.scheduleModalRef(component, schedule);
            });
        }
    }

    scheduleModalRef(component: Component, schedule: ScheduleMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, {size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.schedule = schedule;
        modalRef.result.then((result) => {
            this.router.navigate([{outlets: {popup: null}}], {replaceUrl: true});
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{outlets: {popup: null}}], {replaceUrl: true});
            this.isOpen = false;
        });
        return modalRef;
    }
}
