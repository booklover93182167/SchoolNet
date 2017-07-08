var elementId = 1;
var selectedDate = new Date();
var target = 'BY_TEACHER';

$(document).ready(function () {
    var date = new Date();
    setTableHeader(date);
    getListTeachers();
});

$('.datepicker').datepicker({
    maxViewMode: 0,
    todayBtn: true,
    clearBtn: true,
    autoclose: true,
    todayHighlight: true
});

$('.datepicker').on('changeDate', function (event) {
    setTableHeader(event.date);
    if (getMonday(selectedDate) === getMonday(event.date)){
        return;
    }
    selectedDate = event.date;
    loadSchedule();
});

function getMonday(date) {
    date = new Date(date);
    var day = date.getDay(),
        diff = date.getDate() - day + (day == 0 ? -6 : 1);
    return new Date(date.setDate(diff));
}

function setTableHeader(dateStart) {
    var monday = getMonday(dateStart);
    var tableHeader = $('.table tr').eq(0);
    var tempDateToInsert = monday;
    for (var i = 0; i < 7; i++) {
        tempDateToInsert = new Date(tempDateToInsert.setDate(monday.getDate() + i));
        var day = tempDateToInsert.getDate();
        var weekDay = tempDateToInsert.toLocaleString('en-us', {weekday: 'long'});
        var month = tempDateToInsert.toLocaleString('en-us', {month: 'long'});
        tableHeader.find('th').eq(i + 1).html(weekDay + "<br>" + day + " " + month);
    }
}

$('#target').on('change', function () {
    target = $(this).find("option:selected").val();
    getListOfTargetElements(this.value)
});

function getListOfTargetElements(element) {
    if (element === "teacher") {
        getListTeachers();
    } else if (element === "form") {
        getListForms();
    } else {
        getListClassrooms();
    }
}

function getListTeachers() {
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/teachers",
        type: "GET",
        contentType: "application/json",
        success: function (response) {
            var el = $("#target-element select");
            el.html('');
            if (response[0].teacherId) {
                searchParams.teacherId = response[0].teacherId;
            }
            $.each(response, function () {
                $('<option>')
                    .val(this.id)
                    .text(this.firstName + " " + this.lastName)
                    .appendTo(el);
            });
            if (response[0].id) {
                elementId = response[0].id;
            }
            loadSchedule();
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
            var el = $("#target-element select");
            el.html('');
            $.each(response, function () {
                $('<option>')
                    .val(this.id)
                    .text(this.name)
                    .appendTo(el);
            });
            if (response[0].id) {
                elementId = response[0].id;
            }
            loadSchedule();
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
            var el = $("#target-element select");
            el.html('');
            $.each(response, function () {
                $('<option>')
                    .val(this.id)
                    .text(this.name)
                    .appendTo(el);
            });
            if (response[0].id) {
                elementId = response[0].id;
            }
            loadSchedule();
        },
        error: function (e) {
            console.log(e.message);
        }
    });
}

$('#target-element').on('change', function () {
    elementId = $(this).find("option:selected").val();
    loadSchedule();
});

function loadSchedule() {
    var searchParams = {
        id: elementId,
        scheduleType: target,
        date: selectedDate.toISOString()
    };
    $(".table td:not(:first-child)").html("-");
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/schedule",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(searchParams),
        success: function (response) {
            response.forEach(function (el) {
                if (el.id) {
                    var dayToCorrect = new Date(el.date).getDay();
                    var day = dayToCorrect === 0 ? 7 : dayToCorrect;
                    var selector = $('.table tr').eq(el.lessonPosition);
                    selector.find("td").eq(day).html(el.lessonName + "<br>" + el.classroomName);
                }
            });
        },
        error: function (e) {
            console.log(e.message);
        }
    })
}
