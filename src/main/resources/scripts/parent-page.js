$(function() {
    var today = new Date();
    var minYear = today.getFullYear();
    var maxYear = today.getFullYear();

    if (today.getMonth() < 9 - 1) {
        minYear -= 1;
    } else {
        maxYear += 1;
    }

    $.datepicker.setDefaults($.datepicker.regional["en"]);

    $("#datepicker").datepicker({
        // defaultDate: new Date(),
        dateFormat: "dd.mm.yy",
        minDate: new Date(minYear, 9 - 1, 1),
        maxDate: new Date(maxYear, 8 - 1, 31)
        // showButtonPanel: true
    });

    $("#datepicker").datepicker("setDate", new Date());

    $("#myTab a").click(function () {
        var pupilFormId = $(this).data("pupil-form-id");
        $("#pupilFormId").val(pupilFormId);
        reloadSchedule();
    });

    $("#datepicker").change(function() {
        reloadSchedule();
    });


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

    reloadSchedule();

    function reloadSchedule() {
        var searchParams = {
            pupilFormId: $("#pupilFormId").val(),
            date: $("#datepicker").datepicker("getDate")
        };
        $.ajax({
            url : "/freemarker/parent-home/schedule",
            type : "POST",
            contentType : "application/json",
            data : JSON.stringify(searchParams),
            success : function (response) {

                var daysInWeek = 7;
                var lessonsCount = 10;
                var selectedDate = $("#datepicker").datepicker("getDate");
                var monday = getMonday(selectedDate);

                $("#schedule-wrapper").empty();
                for(var i = 1; i <= daysInWeek; i++) {
                    $("#schedule-wrapper").append('<div class="daytable"><table id="day' + i + '" class="table table-striped">' +
                    '<thead>' +
                        '<tr>' +
                            '<th colspan="5">' + formatDate(addDays(monday, i - 1)) + '</th>' +
                        '</tr>' +
                        '<tr>' +
                            '<th style="width: 2%;">#</th>' +
                            '<th style="width: 40%;">Subject</th>' +
                            '<th style="width: 18%;">Room</th>' +
                            '<th style="width: 40%;">Teacher</th>' +
                            // '<th>Homework</th>' +
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


                // Ajax static table

                // $("#scheduletable tbody").empty();
                // $.each(response, function(j, schedule) {
                //     $('#scheduletable > tbody:last-child').append(
                //         "<tr>" +
                //         "<td>" + schedule.date + "</td>" +
                //         "<td>" + schedule.lessonPosition + "</td>" +
                //         "<td>" + schedule.lessonName + "</td>" +
                //         "<td>" + schedule.classroomName + "</td>" +
                //         "<td>" + schedule.teacherFirstName + " " + schedule.teacherLastName + "</td>" +
                //         "<td>" + schedule.homework + "</td>" +
                //         "</tr>"
                //     );
                // });

            }
        });
    }

});
