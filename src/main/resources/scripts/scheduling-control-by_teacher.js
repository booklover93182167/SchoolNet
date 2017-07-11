var selectedTeacher;
var teacherId;

$('#target-element').on('change', function () {
    selectedTeacher = $(this).find("option:selected").text();
    teacherId = $(this).find("option:selected").val();
});


$('.table td:not(:first-child)').on('click', function () {

// set schedule date to teachers modal window ---------------------------
    $('.modal-body #date').html('');
    var tt = getMonday(selectedDate);
    var date = new Date(tt);
    var newDate = new Date(date);
    var weekDay = $(this).index() - 1;
    newDate.setDate(newDate.getDate() + weekDay);
    $('.modal-body #date').attr('value', newDate.toISOString().slice(0, 10));
// set lesson position to teachers modal window ---------------------------
    var number = $(this).closest("tr").prevAll("tr").length + 1;
    $('.modal-teacher #lesson_position').html('');
    $('<option>')
        .text(number)
        .appendTo($('.modal-teacher #lesson_position'));

    setTeacherToModalWindow();
    loadLessons();
    getListLessonType();
    getListForms();
    getListClassrooms();
});

function setTeacherToModalWindow() {

    $('.modal-teacher #teacher').html('');
    if (!selectedTeacher) {
        selectedTeacher = $('#target-element').find("option:first-child").text()
    }
    $('<option>')
        .val(teacherId)
        .text(selectedTeacher)
        .appendTo($('.modal-teacher #teacher'));
}

function getListLessonType() {
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/lesson-type",
        type: "GET",
        contentType: "application/json",
        success: function (response) {
            fillUpSelectElemnt($('.modal-teacher #lesson_type'), response);
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}

function getListForms() {
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/forms",
        type: "GET",
        contentType: "application/json",
        success: function (response) {
            fillUpSelectElemnt($('.modal-teacher #form_name'), response);
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}

function getListClassrooms() {
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/classrooms",
        type: "GET",
        contentType: "application/json",
        success: function (response) {
            fillUpSelectElemnt($('.modal-teacher #classroom_name'), response);
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}

function loadLessons() {
    var searchParams = {
        id: elementId,
        lessonFilterType: target
    };
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/lessons",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(searchParams),
        success: function (response) {
            fillUpSelectElemnt($('.modal-teacher #lessons'), response);
        },
        error: function (e) {
            console.log(e.message);
        }
    })
}

function fillUpSelectElemnt(target, listOfElements) {
    target.html('');
    $.each(listOfElements, function () {
        $('<option>')
            .val(this.id)
            .text(this.name)
            .appendTo(target);
    })
}
