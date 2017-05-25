export class TeacherManagement {
    constructor(
        public id?: number,
        public enabled?: boolean,
        public userId?: number,
        public formId?: number,
        public schoolId?: number,
    ) {
        this.enabled = false;
    }
}
