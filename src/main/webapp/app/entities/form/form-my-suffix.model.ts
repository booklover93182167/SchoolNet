export class FormMySuffix {
    constructor(
        public id?: number,
        public name?: string,
        public enabled?: boolean,
        public pupilId?: number,
        public scheduleId?: number,
        public schoolId?: number,
        public pupilsId?:object[]
    ) {
        this.enabled = false;
    }
}
