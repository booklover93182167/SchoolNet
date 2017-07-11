var elementId = 1;
var selectedDate = new Date();
var target = 'BY_TEACHER';
var currentElement;
var storedListOfSchedules = [];
var storedListOfClassrooms = [];

$(document).ready(function () {
    var date = new Date();
    setTableHeader(date);
    getListTeachers();
    loadSchedule()
});

$('.datepicker').datepicker({
    maxViewMode: 0,
    todayBtn: true,
    clearBtn: true,
    todayHighlight: true
});

$('.datepicker').on('changeDate', function (event) {
    setTableHeader(event.date);
    if (getMonday(selectedDate) === getMonday(event.date)) {
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
    $('.target_element').attr('data-target', '#' + target);
    getListOfTargetElements(this.value)
});

function getListOfTargetElements(element) {
    if (element === "BY_TEACHER") {
        getListTeachers();
    } else if (element === "BY_FORM") {
        getListForms();
    } else {
        getListClassrooms();
    }
}

$('#target-element').on('change', function () {
    currentElement = $(this).find("option:selected").text();
    elementId = $(this).find("option:selected").val();
    loadSchedule();
});

function getListTeachers() {
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/teachers",
        type: "GET",
        contentType: "application/json",
        success: function (response) {
            fillUpSelectTeachers($('#target-element select'), response);
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

function fillUpSelectTeachers(target, listOfElements) {
    target.html('');
    $.each(listOfElements, function () {
        $('<option>')
            .val(this.id)
            .text(this.firstName + " " + this.lastName)
            .appendTo(target);
    })
}

function getListForms() {
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/forms",
        type: "GET",
        contentType: "application/json",
        success: function (response) {
            fillUpSelectElemnt($('#target-element select'), response);
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
            fillUpSelectElemnt($('#target-element select'), response);
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

// function loadLessons() {
//     var searchParams = {
//         id: elementId,
//         lessonFilterType: target
//     };
//     $.ajax({
//         url: "/freemarker/teacher-mgmt/schedule-mgmt/lessons",
//         type: "POST",
//         contentType: "application/json",
//         data: JSON.stringify(searchParams),
//         success: function (response) {
//             storedListOfLessons = response;
//             fillUpSelectElemnt($('.modal-body #lessons'), storedListOfLessons);
//         },
//         error: function (e) {
//             console.log(e.message);
//         }
//     })
// }

function fillUpSelectElemnt(target, listOfElements) {
    target.html('');
    $.each(listOfElements, function () {
        $('<option>')
            .val(this.id)
            .text(this.name)
            .appendTo(target);
    })
}

function loadSchedule() {
    var searchParams = {
        id: elementId,
        scheduleFilterType: target,
        date: selectedDate.toISOString()
    };
    $(".table td:not(:first-child)").html("-").attr("isEmpty", "true");
    $.ajax({
        url: "/freemarker/teacher-mgmt/schedule-mgmt/schedule",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(searchParams),
        success: function (response) {
            storedListOfSchedules = response;
            response.forEach(function (el) {
                if (el.id) {
                    var dayToCorrect = new Date(el.date).getDay();
                    var day = dayToCorrect === 0 ? 7 : dayToCorrect;
                    var selector = $('.table tr').eq(el.lessonPosition);
                    selector.find("td").eq(day).html(el.lessonName + "<br> Classroom: " + el.classroomName + "<br> Form: " + el.formName)
                        .attr("isEmpty", "false").attr("id", el.id);
                }
            });
        },
        error: function (e) {
            console.log(e.message);
        }
    })
}




function rebuildModalWindow() {
// TODO rebuild modal window on change target elements
}
