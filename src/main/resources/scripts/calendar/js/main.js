$( document ).ready(function() {
    var dateToSend = new Date().toISOString();

    getSchedule(dateToSend);
    lableDate.innerHTML = new Date();
});

var lableDate = document.getElementById("label_date");
var selectedDate;

$(function () {
    $('#calendar').fullCalendar({
        dayClick: function (eventDate) {
            selectedDate = new Date(eventDate);
            var dateToSend = selectedDate.toISOString().slice(0, 10).replace('T', ' ');
            lableDate.innerHTML = selectedDate;
            getSchedule(dateToSend)
            // getAtendance(date);
        },
        theme: true,
        locale: 'en',
    });
});

function getSchedule(date) {
    var request = createRequest();
    if (!request)
    {
        return;
    }
    request.open('GET', 'pupil-home/mySchedule/' + date);
    request.onload = function () {
        var schedule = JSON.parse(request.responseText);
        // renderHTML(schedule, getAtendance(date));
        renderHTMLSchedule(schedule);
    };
    request.send();
}

function getAtendance(date) {
    var request = createRequest();
    var attendances = [];
    if (!request)
    {
        return;
    }
    request.open('GET', 'pupil-home/myAtendance/' + date);
    request.onload = function () {
        attendances = JSON.parse(request.responseText);
    };
    request.send();
    return attendances;
}

function createRequest() {
    var request = false;
    if (window.XMLHttpRequest)
    {
        request = new XMLHttpRequest();
    }
    else if (window.ActiveXObject)
    {
        try
        {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }
        catch (CatchException)
        {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        }
    }
    if (!request)
    {
        alert("Cannot create XMLHttpRequest");
    }
    return request;
}

function renderHTMLSchedule(data) {
    var scheduleTable = document.getElementById("scheduleTable");
    scheduleTable.innerHTML = '';
    var table = '';
    for (var i = 0; i < data.length; i++) {
        table += "<tr><td style=\"width: 40px; text-align: center;\">" + data[i].lessonPosition + "</td>";
        table += "<td style=\"width: 100px; text-align: center;\">" + data[i].lessonName + "</td>";
        table += "<td id=\"homevork\">" + data[i].homework + "</td>";
        table += "<td style=\"width: 75px; text-align: center;\">" + data[i].classroomName + "</td>";
        table += "<td id=\"teacher\" style=\"width: 180px; text-align: center;\">" + data[i].teacherFirstName + " " + data[i].teacherLastName + "</td>";
        table += "<td style=\"width: 40px; text-align: center;\">" + renderHTMLAttendance(data[i]) + "</td></tr>"
    }
    scheduleTable.insertAdjacentHTML("beforeend", table);
}

function renderHTMLAttendance(schedule) {
    return "-";
}
