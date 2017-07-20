$(function() {

    $('[data-toggle="tooltip"]').tooltip();

    $("#sizeSelector").change(function () {
        $("#sizeChangerForm").submit();
    });

    var selectedTd = null;
    var backupValue = null;

    function hideDivShowInput() {
        console.log("hideDivShowInput()");
        selectedTd.find("div").hide();
        selectedTd.find("input").prop("type", "text");
        selectedTd.find("input").focus();
    }

    function hideInputShowDiv() {
        console.log("hideInputShowDiv()");
        selectedTd.find("div").show();
        selectedTd.find("input").prop("type", "hidden");
        selectedTd = null;
        backupValue = null;
    }

    function confirmDiscardChanges() {
        if (selectedTd.find("input").val() != backupValue) {
            if (confirm('Are you sure you want to discard changes?')) {
                selectedTd.find("input").val(backupValue);
                hideInputShowDiv();
            } else {
                return true;
            }
        } else {
            hideInputShowDiv();
        }
        return false;
    }

    $(".attendance").click(function () {
        if ($(this).is(selectedTd)) {
            return;
        }
        if (selectedTd != null) {
            if(confirmDiscardChanges()) {
                return;
            }
        }
        selectedTd = $(this);
        backupValue = $(this).find("input").val();
        hideDivShowInput();
    });

    $("input").bind("enterKey", function(e) {
        var sNewGrade = $(this).val();
        var iNewGrade = parseInt($(this).val(), 10);

        if (sNewGrade) {
            if (iNewGrade < 0 || iNewGrade > 12 || isNaN(iNewGrade)) {
                alert("Invalid grade!");
                return;
            }
        }

        var attendance = {
            grade: iNewGrade,
            enabled: true,
            pupilId: selectedTd.data("pupil-id"),
            scheduleId: selectedTd.data("schedule-id")
        };

        if (selectedTd.data("attendance-id") != -1) {
            attendance.id = selectedTd.data("attendance-id");
        }

        $.ajax({
            url : "/freemarker/teacher-gradebook/update",
            type : "POST",
            contentType : "application/json",
            data : JSON.stringify(attendance),
            success : function (response) {
                var attendanceLog = {
                    date: new Date(Date.now()),
                    oldGrade: backupValue,
                    newGrade: iNewGrade,
                    reason: "",
                    teacherId: $("#teacher-id").val(),
                    attendancesId: response.id
                };
                logAttendance(attendanceLog);

                selectedTd.data("attendance-id", response.id);
                selectedTd.find("input").attr("value", (!sNewGrade ? "" : response.grade));
                selectedTd.find("div").text((!sNewGrade ? "-" : response.grade));
                hideInputShowDiv();
            },
            error: function() {
                alert("Error while editing attendance");
            }
        });
    });

    function logAttendance(attendance) {
        $.ajax({
            url : "/freemarker/teacher-gradebook/log-attendance",
            type : "POST",
            contentType : "application/json",
            data : JSON.stringify(attendance),
            success : function (response) {
                console.log("Attendance successfully logged");
            },
            error: function() {
                console.log("Error while logging attendance");
            }
        });
    }

    $("input").keyup(function(e){
        if (e.keyCode === 13) {
            $(this).trigger("enterKey");
        }
    });

    $(document).keyup(function(e){
        if (e.keyCode === 27) {
            selectedTd.find("input").val(backupValue);
            hideInputShowDiv();
        }
    });

});
