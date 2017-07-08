export class ScheduleMySuffix {
    constructor(
        public id?: number,
        public date?: any,
        public homework?: string,
        public lessonPosition?: number,
        public enabled?: boolean,
        public attendancesId?: number,
        public lessonId?: number,
        public formId?: number,
        public classroomId?: number,
        public classroomName?: string,
        public teacherId?: number,
        public tempTeacherLastName?: string,
        public tempTeacherFirstName?: string,
        public courseId?: number,
        public formName?: string,
        public lessonName?: string,
        public teacherLastName?: string,
        public teacherFirstName?: string,
        public lessonTypeId?: number,
        public lessonTypeName?: string,
    ) {
        this.enabled = false;
    }
}
