$(document).ready(function () {
    var dateToSend = new Date().toISOString().slice(0, 19);

    getSchedule(dateToSend);
    lableDate.innerHTML = new Date();
});

var lableDate = document.getElementById("label_date");
var selectedDate;
var attendances;
$(function () {
    $('#calendar').fullCalendar({
        dayClick: function (eventDate) {
            selectedDate = new Date(eventDate);
            var dateToSend = selectedDate.toISOString().slice(0, 19);
            lableDate.innerHTML = selectedDate;
            getSchedule(dateToSend)
            // getAttendance(dateToSend);
        },
        theme: true,
        locale: 'en',
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
        getAttendance(date);
        renderHTMLTable(schedule, attendances);
    };
    request.send();
}

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

function renderHTMLTable(data, attendance) {
    var scheduleTable = document.getElementById("scheduleTable");
    scheduleTable.innerHTML = '';
    var table = '';
    for (var i = 0; i < data.length; i++) {

        table += "<tr><td style=\"width: 40px; text-align: center;\">" + data[i].lessonPosition + "</td>";
        table += "<td style=\"width: 100px; text-align: center;\">" + data[i].lessonName + "</td>";
        table += "<td id=\"homevork\">" + data[i].homework + "</td>";
        table += "<td style=\"width: 75px; text-align: center;\">" + data[i].classroomName + "</td>";
        table += "<td id=\"teacher\" style=\"width: 180px; text-align: center;\">" + data[i].teacherFirstName + " " + data[i].teacherLastName + "</td>";
        var grade = (data[i].id) ? renderHTMLAttendance(data[i].id, attendance) : "-";
        table += "<td style=\"width: 40px; text-align: center;\">" + grade + "</td></tr>"
    }
    scheduleTable.insertAdjacentHTML("beforeend", table);
}

function renderHTMLAttendance(id, attendances) {
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
