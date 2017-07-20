var filterTypeForModalTeacher = 'BY_TEACHER';
var dateForTeacherModal = new Date();
var formIdFromCurrentSchedule;
var classroomIdFromCurrentSchedule;
var scheduleToEdit;
var scheduleId;
var selectedTeacher;
var selectedTeacherId = 1;
var selectedLessonId;
var selectedLesson_typeId = 1;
var selectedLessonPosition;
var selectedFormId;
var selectedClassroomId;
var isEmpty = true;

$('#target-element').on('change', function () {
    selectedTeacher = $(this).find("option:selected").text();
    selectedTeacherId = $(this).find("option:selected").val();
});

$('.modal-teacher #teacher').on('change', function () {
    selectedTeacherId = $(this).find("option:selected").val();
    loadLessons($('.modal-teacher #lessons'), filterTypeForModalTeacher);
});

$('.modal-teacher #lessons').on('change', function () {
    selectedLessonId = $(this).find("option:selected").val();
});

$('.modal-teacher #lesson_type').on('change', function () {
    selectedLesson_typeId = $(this).find("option:selected").val();
});

$('.modal-teacher #form_name').on('change', function () {
    selectedFormId = $(this).find("option:selected").val();
});

$('.modal-teacher #classroom_name').on('change', function () {
    selectedClassroomId = $(this).find("option:selected").val();
});

$('.table td:not(:first-child)').on('click', function () {
    selectedLessonPosition = $(this).closest("tr").prevAll("tr").length + 1;
    scheduleId = $(this).attr('id');
    datePrepare($(this).index() - 1);
    if (scheduleId) {
        // $('.modal-teacher #teacher_input').prop('disabled', false);
        getListTeachersForModals();
        loadLessons($('.modal-teacher #lessons'), filterTypeForModalTeacher);
        getListLessonType($('.modal-teacher #lesson_type'));

        loadCurrentSchedule();

    } else {
        $('.modal-teacher #teacher_input').prop('disabled', true);
        // set schedule date to teachers modal window ---------------------------
        $('.modal-body #date').html('');
        $('.modal-teacher #date').attr('value', dateForTeacherModal.toISOString().slice(0, 10));
        // set lesson position to teachers modal window ---------------------------

        fillUpLessonPosition($('.modal-teacher #lesson_position'), selectedLessonPosition);
        setTeacherToModalWindow();
        loadLessons($('.modal-teacher #lessons'), filterTypeForModalTeacher);
        getListLessonType($('.modal-teacher #lesson_type'));
        getListFormsForModal($('.modal-teacher #form_name'));
        getListClassroomsForModal($('.modal-teacher #classroom_name'));
    }
});

function datePrepare(weekDay) {
    var tt = getMonday(selectedDate);
    var newDate = new Date(tt);
    dateForTeacherModal.setDate(newDate.getDate() + weekDay);
}

function fillUpLessonPosition(target, lessonPosition) {
    target.html('');
    $('<option>')
        .text(lessonPosition)
        .val(lessonPosition)
        .appendTo(target);
}

function setTeacherToModalWindow() {

    $('.modal-teacher #teacher').html('');
    if (!selectedTeacher) {
        selectedTeacher = $('#target-element').find("option:first-child").text()
    }
    $('<option>')
        .val(selectedTeacherId)
        .text(selectedTeacher)
        .appendTo($('.modal-teacher #teacher'));
}

function getListLessonType(target) {
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/lesson-type",
        type: "GET",
        contentType: "application/json",
        success: function (response) {
            fillUpSelectElement(target, response);
            if (response[0]) {
                selectedLesson_typeId = response[0].id
            }
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}

function getListFormsForModal(target) {
    var searchParams = {
        formId: formIdFromCurrentSchedule,
        lessonPosition: selectedLessonPosition,
        date: dateForTeacherModal
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/forms-wp",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(searchParams),
        success: function (response) {
            fillUpSelectElement(target, response);
            formIdFromCurrentSchedule = '';
            if (response[0]) {
                selectedFormId = response[0].id
            }
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}

function getListClassroomsForModal(target) {

    var searchParams = {
        classroomId: classroomIdFromCurrentSchedule,
        lessonPosition: selectedLessonPosition,
        date: dateForTeacherModal
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/classrooms-wp",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(searchParams),
        success: function (response) {
            fillUpSelectElement(target, response);
            classroomIdFromCurrentSchedule = ''
            if (response[0]) {
                selectedClassroomId = response[0].id
            }
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}

function loadLessons(target, filterType) {
    var searchParams = {
        id: selectedTeacherId,
        filterType: filterType
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/lessons",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(searchParams),
        success: function (response) {
            fillUpSelectElement(target, response);
            if (response[0]) {
                selectedLessonId = response[0].id
            }
        },
        error: function (e) {
            console.log(e.message);
        }
    })
}

function fillUpSelectElement(target, listOfElements) {
    target.html('');
    $.each(listOfElements, function () {
        $('<option>')
            .val(this.id)
            .text(this.name)
            .appendTo(target);
    })
}

function fillUpSelectTeachers(target, listOfElements) {
    target.html('');
    $.each(listOfElements, function () {
        $('<option>')
            .val(this.id)
            .text(this.firstName + " " + this.lastName)
            .appendTo(target);
    })
}

function loadCurrentSchedule() {
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/schedule/" + scheduleId,
        type: "GET",
        contentType: "application/json",
        success: function (response) {
            scheduleToEdit = response;
            formIdFromCurrentSchedule = response.formId;
            selectedFormId = response.formId;
            classroomIdFromCurrentSchedule = response.classroomId;
            selectedClassroomId = response.classroomId;
            selectedLessonId = response.lessonId;
            selectedLesson_typeId = response.lessonTypeId;


            getListFormsForModal($('.modal-teacher #form_name'));
            // classroom
            getListClassroomsForModal($('.modal-teacher #classroom_name'));
            $('.modal-teacher #classroom_name').val(response.classroomId); // TODO: doesn't works correctly

            // set date from stored schedule
            $('.modal-body #date').html('').attr('value', response.date.slice(0, 10));
            // set lesson position
            fillUpLessonPosition($('.modal-teacher #lesson_position'), response.lessonPosition);
            // teacher
            $('.modal-teacher #teacher_input').val(response.teacherId);
            // lesson
            $('.modal-teacher #lessons').val(response.lessonId);
            // lesson type
            $('.modal-teacher #lesson_type').val(response.lessonTypeId);
            // form
            $('.modal-teacher #form_name').val(response.formId);


        },
        error: function (e) {
            console.log(e.message);
        }
    })
}

function getListTeachersForModals() { // TODO: change to 'POST' method with searching parameters: date, lessonPosition.
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/teachers",
        type: "GET",
        contentType: "application/json",
        success: function (response) {
            fillUpSelectTeachers($('.modal-teacher #teacher'), response);
            if (response[0])
                selectedTeacherId = response[0].id;
        },

        error: function (e) {
            console.log(e.message);
        }
    });
}

function saveSchedule() {
    if (scheduleId) {
        updateSchedule();
    } else {
        saveNewSchedule();
    }
}

function updateSchedule() {
    var schedule = {
        id: scheduleToEdit.id,
        date: scheduleToEdit.date,
        enabled: true,
        lessonPosition: scheduleToEdit.lessonPosition,
        teacherId: selectedTeacherId,
        lessonId: selectedLessonId,
        lessonTypeId: selectedLesson_typeId,
        formId: selectedFormId,
        classroomId: selectedClassroomId
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/schedule-update",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(schedule),
        success: function (response) {
            fillUpOneScheduleFieldInTable(response);
        },
        error: function (e) {
            console.log(e.message);
        }
    })
}

function saveNewSchedule() {
    var schedule = {
        date: dateForTeacherModal,
        enabled: true,
        lessonPosition: selectedLessonPosition,
        teacherId: selectedTeacherId,
        lessonId: selectedLessonId,
        lessonTypeId: selectedLesson_typeId,
        formId: selectedFormId,
        classroomId: selectedClassroomId
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/schedule-create",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(schedule),
        success: function (response) {
            fillUpOneScheduleFieldInTable(response);
        },
        error: function (e) {
            console.log(e.message);
        }
    })
}
