$(function() {
    var pupilFormId = 0;
    var selectedDate = new Date();
    var monday = getMonday(selectedDate);
    var minYear = selectedDate.getFullYear();
    var maxYear = selectedDate.getFullYear();
    var lang = $("#locale").val();

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

                for(var i = 1; i <= daysInWeek; i++) {
                    $('#day' + i + ' thead tr th').eq(0).text($.datepicker.formatDate('DD, dd.mm.yy', addDays(monday, i - 1)));
                }

                $(".for-clear").empty();
                $.each(response, function(j, schedule) {
                    var dayOfWeek = new Date(schedule.date).getDay();
                    var selector = $('#day' + dayOfWeek + ' tbody tr').eq(schedule.lessonPosition);

                    selector.prop("title", schedule.homework);
                    selector.find("td").eq(1).text(schedule.lessonName);
                    selector.find("td").eq(2).text(schedule.classroomName);
                    selector.find("td").eq(3).text(schedule.teacherFirstName + " " + schedule.teacherLastName);
                });
            }
        });
    }
});
