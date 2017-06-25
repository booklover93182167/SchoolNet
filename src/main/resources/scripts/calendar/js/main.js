$(document).ready(function () {
    var dateToSend = new Date().toISOString().slice(0, 19);

    getSchedule(dateToSend);
    lableDate.innerHTML = new Date().toDateString();
});

var lableDate = document.getElementById("label_date");

$.urlParam = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results == null) {
        return null;
    }
    else {
        return decodeURI(results[1]) || 0;
    }
};

var selectedDate;
var schedules;

$(function () {
    $('#calendar').fullCalendar({
        dayClick: function (eventDate) {
            selectedDate = new Date(eventDate);
            var dateToSend = selectedDate.toISOString().slice(0, 19);
            lableDate.innerHTML = selectedDate.toDateString();
            getSchedule(dateToSend)
        },
        theme: true,
        locale: $.urlParam('lang')
    });
});

function getSchedule(date) {
    var request = createRequest();
    if (!request) {
        return;
    }
    request.open('GET', 'pupil-home/mySchedule/' + date);
    request.onload = function () {
        schedules = JSON.parse(request.responseText);
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
        schedules.forEach(function (el) {
            if (el.id) {
                var selector = $('table tr').eq(el.lessonPosition);
                selector.find("td").eq(1).html(el.lessonName);
                selector.find("td").eq(2).html(el.homework).addClass("homework");
                selector.find("td").eq(3).html(el.classroomName);
                selector.find("td").eq(4).html(el.teacherLastName + " " + el.teacherFirstName).addClass("myTeacher");
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
