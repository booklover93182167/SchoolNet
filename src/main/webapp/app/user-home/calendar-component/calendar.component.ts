/**
 * Created by Kolja on 24.05.2017.
 */
import { Component, ChangeDetectionStrategy, ViewEncapsulation, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CalendarEvent, CalendarMonthViewDay } from 'angular-calendar';

@Component({
    selector: 'mwl-demo-component',
    changeDetection: ChangeDetectionStrategy.OnPush,
    templateUrl: 'calendar.component.html',
    styleUrls: ['calendar.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class CalendarComponent {


    viewDate: Date = new Date();

    selectedDay: CalendarMonthViewDay;

    events: CalendarEvent[] = [];

    @Input() excludeDays: number[] = [];
    @Input() weekStartsOn: number;


    selectDay: (day: CalendarMonthViewDay) => void;

    constructor() {
        this.selectDay = (day: CalendarMonthViewDay): void => {
            if (this.selectedDay && day.date.getTime() === this.selectedDay.date.getTime()) {
                day.cssClass = 'cal-day-selected';
            }
        };
    }

    dayClicked(day: CalendarMonthViewDay): void {
        this.selectedDay = day;
        console.log(this.selectedDay.date);
    }


}
