export class TeacherClass {
    constructor(
        public id?: number,
        public enabled?: boolean,
        public formId?: number,
        public teacherId?: number,
        public formName?: string,
        public classroomName?: string,
    ) {
        this.enabled = false;
    }
}
