import {Pipe, PipeTransform} from '@angular/core';
import {ScheduleMySuffix} from '../entities/schedule/schedule-my-suffix.model';
/**
 * Created by slavkosoltys on 05.06.17.
 */
@Pipe({
    name: 'sortByDate'
})

export class TeacherScheduleSortByDatePipe implements PipeTransform {
    transform(array: ScheduleMySuffix[]): ScheduleMySuffix[] {
        array.sort((a: ScheduleMySuffix, b: ScheduleMySuffix) => {
            if (a.date < b.date) {
                return -1;
            } else if (a.date > b.date) {
                return 1;
            } else {
                return 0;
            }
        });
        return array;
    }
}

@Pipe({
    name: 'lengthPipe'
})
export class LengthPipe {
    transform(value: string, args: string[]): string {
        let limit = args.length > 0 ? parseInt(args[0], 10) : 20;
        let trail = args.length > 1 ? args[1] : '...';

        return value.length > limit ? value.substring(0, limit) + trail : value;
    }
}
