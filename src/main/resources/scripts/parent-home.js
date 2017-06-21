$(function() {
    var pupilFormId = 0;
    var selectedDate = new Date();
    var monday = getMonday(selectedDate);
    var minYear = selectedDate.getFullYear();
    var maxYear = selectedDate.getFullYear();
    var lang = $("#lang").val();

    if (selectedDate.getMonth() < 9 - 1) {
        minYear -= 1;
    } else {
        maxYear += 1;
    }

    $.datepicker.setDefaults($.datepicker.regional[lang]);

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
            return;
        }
        monday = newMonday;
        reloadSchedule();
    });

    $("#myTab a").click(function () {
        var newPupilFormId = $(this).data("pupil-form-id");

        if (pupilFormId === newPupilFormId) {
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

    function reloadSchedule() {
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
                            '<th colspan="5">' + $.datepicker.formatDate('DD, dd.mm.yy', addDays(monday, i - 1)) + '</th>' +
                        '</tr>' +
                        '<tr>' +
                            '<th style="width: 2%;"><span class="lesson-position"></span></th>' +
                            '<th style="width: 40%;"><span class="subject"></span></th>' +
                            '<th style="width: 18%;"><span class="classroom"></span></th>' +
                            '<th style="width: 40%;"><span class="teacher"></span></th>' +
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

                $(".lesson-position").text("#");
                $(".subject").text("Subject");
                $(".classroom").text("Room");
                $(".teacher").text("Teacher");
            }
        });
    }

});
