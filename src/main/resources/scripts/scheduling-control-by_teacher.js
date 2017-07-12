var filterTypeForModalTeacher = 'BY_TEACHER';
var dateForTeacherModal = new Date();
var scheduleToEdit;
var scheduleId;
var selectedTeacher;
var selectedTeacherId = 1;
var selectedLessonPosition;


$('#target-element').on('change', function () {
    selectedTeacher = $(this).find("option:selected").text();
    selectedTeacherId = $(this).find("option:selected").val();
});

$('.modal-teacher #teacher').on('change', function () {
    selectedTeacherId = $(this).find("option:selected").val();
    loadLessons($('.modal-teacher #lessons'), filterTypeForModalTeacher);
});

$('.table td:not(:first-child)').on('click', function () {
    selectedLessonPosition = $(this).closest("tr").prevAll("tr").length + 1;
    scheduleId = $(this).attr('id');
    datePrepare($(this).index() - 1);
    if (scheduleId) {
        $('.modal-teacher #teacher_input').prop('disabled', false);
        getListTeachersForModals();
        loadLessons($('.modal-teacher #lessons'), filterTypeForModalTeacher);
        getListLessonType($('.modal-teacher #lesson_type'));
        getListFormsForModal($('.modal-teacher #form_name'));
        getListClassroomsForModal($('.modal-teacher #classroom_name'));

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
    nullifyTimeInDate(dateForTeacherModal);
}

function fillUpLessonPosition(target, lessonPosition) { // TODO: it doesn't works correctly
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
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}

function getListFormsForModal(target) {
    var searchParams = {
        lessonPosition: selectedLessonPosition,
        date: dateForTeacherModal.toISOString().slice(0, 19)
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/forms-wp",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(searchParams),
        success: function (response) {
            fillUpSelectElement(target, response);
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}

function getListClassroomsForModal(target) {

    var searchParams = {
        lessonPosition: selectedLessonPosition,
        date: dateForTeacherModal.toISOString().slice(0, 19)
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/classrooms-wp",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(searchParams),
        success: function (response) {
            fillUpSelectElement(target, response);
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}

function loadLessons(target, filterType) {
    var searchParams = {
        id: selectedTeacherId,
        lessonFilterType: filterType
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/lessons",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(searchParams),
        success: function (response) {
            fillUpSelectElement(target, response);
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

function saveSchedule() {
    var schedule = {
        date: '',
        enabled: '',
        lessonPosition: '',
        teacherId: '',
        lessonId: '',
        lessonTypeId: '',
        formId: '',
        classroomId: ''
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/schedule-create",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(schedule),
        success: function (response) {
            alert('Save!')
            // fillUpScheduleTable(response);

        },
        error: function (e) {
            console.log(e.message);
        }
    })
}

function loadCurrentSchedule() {
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/schedule/" + scheduleId,
        type: "GET",
        contentType: "application/json",
        success: function (response) {
            scheduleToEdit = response;

            // set date from stored schedule
            $('.modal-body #date').html('').attr('value', response.date.slice(0, 10));
            // set lesson position
            fillUpLessonPosition($('.modal-teacher #lesson_position'), response.lessonPosition);
            // teacher

            // lesson

            // lesson type

            // form

            // classroom


        },
        error: function (e) {
            console.log(e.message);
        }
    })
}

function getListTeachersForModals() {
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

function updateSchedule() {
    var schedule = {
        id: '',
        date: '',
        enabled: '',
        lessonPosition: '',
        teacherId: '',
        lessonId: '',
        lessonTypeId: '',
        formId: '',
        classroomId: ''
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/schedule-create",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(schedule),
        success: function (response) {

            fillUpScheduleTable(response);

        },
        error: function (e) {
            console.log(e.message);
        }
    })
}

