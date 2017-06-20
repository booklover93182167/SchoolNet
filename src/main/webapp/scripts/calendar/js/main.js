var scheduleTable = document.getElementById("scheduleTable");
var lableDate = document.getElementById("label_date");
lableDate.innerHTML = new Date().toDateString();

var selectedDate;

$(function () {
    $('#calendar').fullCalendar({
        dayClick: function (eventDate) {
            selectedDate = new Date(eventDate);
            var dateToSend = selectedDate.toISOString();
            // alert('Clicked on: ' + dateToSend);
            lableDate.innerHTML = selectedDate;

            var request = new XMLHttpRequest();
            request.open('GET', 'pupil-home/mySchedule/' + dateToSend);
            request.onload = function () {
                var schedule = JSON.parse(request.responseText);
                renderHTML(schedule);
            };
            request.send();
        },
        theme: true,
        locale: 'en',
    });
});

function renderHTML(data) {
    scheduleTable.innerHTML = '';
    var table = "<label> My schedule on " + selectedDate + "</label>";
    table += "<table class=\"table table-striped\"><tr>" +
        "<th>Position</th> <th>Lesson</th><th>Homework</th><th>Classroom</th> <th>Teacher</th></tr>";
    for (var i = 0; i < data.length; i++) {
        table += "<tr><td>" + data[i].lessonPosition + "</td>";
        table += "<td>" + data[i].lessonName + "</td>";
        table += "<td>" + data[i].homework + "</td>";
        table += "<td>" + data[i].classroomName + "</td>";
        table += "<td>" + data[i].teacherFirstName + " " + data[i].teacherLastName + "</td></tr>";
    }
    table += "</table>";
    scheduleTable.insertAdjacentHTML("beforeend", table);
}
