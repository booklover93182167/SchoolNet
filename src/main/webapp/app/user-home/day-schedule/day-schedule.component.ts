import { Component, OnInit } from '@angular/core';
import {UserHomeService} from "../user-home.service";

@Component({
  selector: 'day-schedule',
  templateUrl: './day-schedule.component.html',
  styles: []
})
export class DayScheduleComponent implements OnInit {

    name: string = "vova";
    searchDateToSend: any;

    constructor(private userHomeService: UserHomeService) {
        this.userHomeService.dateToSend$.subscribe(
            data => {
                console.log('Sibling1Component-received from sibling2: ' + data);
                this.searchDateToSend = data;
            });

    }

    ngOnInit() {

    }



}





