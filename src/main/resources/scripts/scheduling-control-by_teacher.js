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
    loadLessons();
});

$('.table td:not(:first-child)').on('click', function () {
    selectedLessonPosition = $(this).closest("tr").prevAll("tr").length + 1;
    scheduleId = $(this).attr('id');
    if (scheduleId) {
        $('.modal-teacher #teacher_input').prop('disabled', false);
        fillUpLessonPosition();
        getListTeachersForModals();
        loadLessons();
        getListLessonType();
        getListFormsForModal();
        getListClassroomsForModal();

        loadCurrentSchedule();

    } else {
        $('.modal-teacher #teacher_input').prop('disabled', true);
        // set schedule date to teachers modal window ---------------------------
        $('.modal-body #date').html('');
        var tt = getMonday(selectedDate);
        var date = new Date(tt);
        var newDate = new Date(date);
        var weekDay = $(this).index() - 1;
        newDate.setDate(newDate.getDate() + weekDay);
        selectedDate = newDate.toISOString().slice(0, 19);
        $('.modal-teacher #date').attr('value', newDate.toISOString().slice(0, 10));
        // set lesson position to teachers modal window ---------------------------

        fillUpLessonPosition();
        setTeacherToModalWindow();
        loadLessons();
        getListLessonType();
        getListFormsForModal();
        getListClassroomsForModal();
    }
});

function fillUpLessonPosition() {
    // $('.modal-teacher #lesson_position').html('');;
    $('<option>')
        .text(selectedLessonPosition)
        .val(selectedLessonPosition)
        .appendTo($('.modal-teacher #lesson_position'));
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

function getListLessonType() {
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/lesson-type",
        type: "GET",
        contentType: "application/json",
        success: function (response) {
            fillUpSelectElement($('.modal-teacher #lesson_type'), response);
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}

function getListFormsForModal() {
    var searchParams = {
        lessonPosition: selectedLessonPosition,
        date: selectedDate
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/forms-wp",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(searchParams),
        success: function (response) {
            fillUpSelectElement($('.modal-teacher #form_name'), response);
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}

function getListClassroomsForModal() {
    var searchParams = {
        lessonPosition: selectedLessonPosition,
        date: selectedDate
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/classrooms-wp",
        // type: "GET",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(searchParams),
        success: function (response) {
            fillUpSelectElement($('.modal-teacher #classroom_name'), response);
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}

function loadLessons() {
    var searchParams = {
        id: selectedTeacherId,
        lessonFilterType: 'BY_TEACHER'
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/lessons",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(searchParams),
        success: function (response) {
            fillUpSelectElement($('.modal-teacher #lessons'), response);
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

            fillUpScheduleTable(response);

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
            fillUpLessonPosition();
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
                teacherId = response[0].id;
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

