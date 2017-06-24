$(document).ready(function () {
    var dateToSend = new Date().toISOString().slice(0, 19);

    getSchedule(dateToSend);
    lableDate.innerHTML = new Date();
});

var lableDate = document.getElementById("label_date");
var selectedDate;

$(function () {
    $('#calendar').fullCalendar({
        dayClick: function (eventDate) {
            selectedDate = new Date(eventDate);
            var dateToSend = selectedDate.toISOString().slice(0, 19);
            lableDate.innerHTML = selectedDate.toDateString();
            getSchedule(dateToSend)
        },
        theme: true,
        locale: 'en'
    });
});

function getSchedule(date) {
    var request = createRequest();
    if (!request) {
        return;
    }
    request.open('GET', 'pupil-home/mySchedule/' + date);
    request.onload = function () {
        var schedule = JSON.parse(request.responseText);
        var attendances;
        getAttendance(date);
        function getAttendance(date) {
            var request = createRequest();

            if (!request) {
                return;
            }
            request.open('GET', 'pupil-home/myAttendances/' + date, false);
            request.onload = function () {
                attendances = JSON.parse(request.responseText);
            };
            request.send();
        }
        $(".default").html("-");
        schedule.forEach(function (el) {
            if (el.id) {
                var selector = $('table tr').eq(el.lessonPosition);
                selector.find("td").eq(1).html(el.lessonName);
                selector.find("td").eq(2).html(el.homework);
                selector.find("td").eq(3).html(el.classroomName);
                selector.find("td").eq(4).html(el.teacherLastName + " " + el.teacherFirstName);
                selector.find("td").eq(5).html(pickUpAttendance(el.id, attendances));
            }
        });
    };
    request.send();
}

function pickUpAttendance(id, attendances) {
    var grade = 'n/a';
    attendances.forEach(function (attendance) {
        if (attendance.scheduleId === id) {
            if (attendance.grade != 0) {
                grade = attendance.grade;
            }
        }
    });
    return grade;
}

function createRequest() {
    var request = false;
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        try {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (CatchException) {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        }
    }
    if (!request) {
        alert("Cannot create XMLHttpRequest");
    }
    return request;
}
