/**
 * Created by User on 08.06.2017.
 */
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: "sortPipe"
})
export  class SortPipe implements PipeTransform{
    transform(value: any[]) : any {
        return  value.sort().reverse();
    }
}
