export class Course {
    constructor(
        public id?: number,
        public enabled?: boolean,
        public formId?: number,
        public lessonId?: number,
        public teacherId?: number,
        public formName?: string,
        public lessonName?: string,
        public teacherFirstName?: string,
        public teacherLastName?: string,
    ) {
        this.enabled = false;
    }
}
