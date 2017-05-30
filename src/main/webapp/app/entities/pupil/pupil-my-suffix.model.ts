

export class PupilMySuffix {
    constructor(
        public id?: number,
        public enabled?: boolean,
        public userId?: number,
        public attendancesId?: number,
        public formId?: number,
        public parentsId?: number[],
        public lastName?: string,
        public firstName?: string
    ) {
        this.enabled = false;
    }
}
