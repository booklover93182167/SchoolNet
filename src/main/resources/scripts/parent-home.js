$(function() {
    var pupilFormId = 0;
    var selectedDate = new Date();
    var monday = getMonday(selectedDate);
    var minYear = selectedDate.getFullYear();
    var maxYear = selectedDate.getFullYear();

    if (selectedDate.getMonth() < 9 - 1) {
        minYear -= 1;
    } else {
        maxYear += 1;
    }

    $.datepicker.setDefaults($.datepicker.regional["en"]);

    $("#datepicker").datepicker({
        dateFormat: "dd.mm.yy",
        minDate: new Date(minYear, 9 - 1, 1),
        maxDate: new Date(maxYear, 8 - 1, 31)
        // showButtonPanel: true
        // defaultDate: selectedDate
    });

    $("#datepicker").change(function() {
        selectedDate = $(this).datepicker("getDate");
        var newMonday = getMonday(selectedDate);

        if (monday.getTime() === newMonday.getTime()) {
            console.log("NO reload");
            return;
        }
        monday = newMonday;
        reloadSchedule();
    });

    $("#myTab a").click(function () {
        var newPupilFormId = $(this).data("pupil-form-id");

        if (pupilFormId === newPupilFormId) {
            console.log("NO reload");
            return;
        }
        pupilFormId = newPupilFormId;
        reloadSchedule();
    });

    $("#myTab a:first").trigger("click");

    function getMonday(date) {
        var newDate = new Date(date);
        var day = newDate.getDay();
        var diff = newDate.getDate() - day + (day == 0 ? -6 : 1);
        return new Date(newDate.setDate(diff));
    }

    function addDays(date, days) {
        var newDate = new Date(date);
        newDate.setDate(date.getDate() + days);
        return newDate;
    }

    function formatDate(date) {
        var dayNames = [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        var dayOfWeek = date.getDay();
        var day = date.getDate();
        var month = date.getMonth();
        var year = date.getFullYear();

        return dayNames[dayOfWeek] + ', ' + day + '.' + month + '.' + year;
    }

    function reloadSchedule() {
        console.log("YES reload");
        var searchParams = {
            pupilFormId: pupilFormId,
            date: selectedDate
        };
        $.ajax({
            url : "/freemarker/parent-home/schedule",
            type : "POST",
            contentType : "application/json",
            data : JSON.stringify(searchParams),
            success : function (response) {

                var daysInWeek = 7;
                var lessonsCount = 10;

                $("#week-schedule").empty();
                for(var i = 1; i <= daysInWeek; i++) {
                    $("#week-schedule").append('<div class="day-schedule"><table id="day' + i + '" class="table table-striped">' +
                    '<thead>' +
                        '<tr>' +
                            '<th colspan="5">' + formatDate(addDays(monday, i - 1)) + '</th>' +
                        '</tr>' +
                        '<tr>' +
                            '<th style="width: 2%;">#</th>' +
                            '<th style="width: 40%;">Subject</th>' +
                            '<th style="width: 18%;">Room</th>' +
                            '<th style="width: 40%;">Teacher</th>' +
                        '</tr>' +
                    '</thead>' +
                    '<tbody></tbody>' +
                    '</table></div>');
                    for(var j = 1; j <= lessonsCount; j++) {
                        $('#day' + i + ' > tbody:last-child').append(
                            '<tr>' +
                                '<td>' + j + '</td>' +
                                '<td></td>' +
                                '<td></td>' +
                                '<td></td>' +
                            '</tr>'
                        );
                    }
                }

                $.each(response, function(j, schedule) {
                    var dayOfWeek = new Date(schedule.date).getDay();

                    $('#day' + dayOfWeek + ' tbody tr').eq(schedule.lessonPosition).replaceWith(
                        '<tr title="Homework: ' + schedule.homework + '">' +
                        '<td>' + (schedule.lessonPosition + 1) + '</td>' +
                        '<td>' + schedule.lessonName + '</td>' +
                        '<td>' + schedule.classroomName + '</td>' +
                        '<td>' + schedule.teacherFirstName + " " + schedule.teacherLastName + '</td>' +
                        '</tr>'
                    );
                });

            }
        });
    }

});
