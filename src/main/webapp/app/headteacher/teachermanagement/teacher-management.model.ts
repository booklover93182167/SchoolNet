export class TeacherManagement {
    constructor(
        public id?: number,
        public enabled?: boolean,
        public userId?: number,
        public formId?: number,
        public schoolId?: number,
        public teacherFirstName?: string,
        public teacherLastName?: string,
        public teacherEmail?: string,
        public email?: string,
        public firstName?: string,
        public lastName?: string,
    ) {
        this.enabled = false;
    }
}
