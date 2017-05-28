import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
    name: 'mapToIterable'
})
export class PupilHomeSchedulesMapToIterablePipe {
    transform(value: any) {
        console.log('entered mappipe');
        return Object.keys(value).map((key) => value[key]);
    }
}
