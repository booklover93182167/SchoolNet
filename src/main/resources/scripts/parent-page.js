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
        maxDate: new Date(maxYear, 8 - 1, 31),
        showButtonPanel: true
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
                /*
                 $.each(response, function(i, date) {
                 console.log("date: " + date.date.substring(0,10));
                 });
                 */
                $("#scheduletable tbody").empty();
                $.each(response, function(j, schedule) {
                    $('#scheduletable > tbody:last-child').append(
                        "<tr>" +
                        "<td>" + schedule.date + "</td>" +
                        "<td>" + schedule.lessonPosition + "</td>" +
                        "<td>" + schedule.lessonName + "</td>" +
                        "<td>" + schedule.classroomName + "</td>" +
                        "<td>" + schedule.teacherFirstName + " " + schedule.teacherLastName + "</td>" +
                        "<td>" + schedule.homework + "</td>" +
                        "</tr>"
                    );
                });
            }
        });
    }
});
