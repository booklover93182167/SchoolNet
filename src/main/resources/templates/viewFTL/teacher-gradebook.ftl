<#import "header.ftl" as h>

<#assign pagetitle><@spring.message "teacher.gradebook.page.title"/></#assign>

<@h.header
pagetitle = "${pagetitle}"
/>

<style>
.gradebook tr th {
    vertical-align: middle;
}
.gradebook .date,
.gradebook .attendance {
    text-align: center;
}
.gradebook tr th.number {
    width: 2%;
}
.gradebook tr th.fullname {
}
.gradebook tr th.date {
    width: 6%;
}
</style>

<div class="container">
    <div class="row content">
        <div class="col-sm-12 text-left">

            <br>
            <h1>${pagetitle} ${model["teacher"]}</h1>

            <br>
            <p><@spring.message "teacher.gradebook.description"/></p>

            <#list model["formsAndLessons"]>
            <ul class="nav nav-pills" id="forms-lessons">
                <#items as formAndLesson>
                <li class="nav-item">
                    <a class="nav-link <#if model["formId"] == formAndLesson.formId && model["lessonId"] == formAndLesson.lessonId>active</#if>" href="/freemarker/teacher-gradebook/${formAndLesson.formId}/${formAndLesson.lessonId}">${formAndLesson.formName} ${formAndLesson.lessonName}</a>
                </li>
                </#items>
            </ul>
            </#list>

            <br>
            <table class="table table-striped gradebook">
            <#list model["pupils"]>

                <tr>
                    <th class="number">#</th>
                    <th class="fullname">Full Name</th>
                    <#list model["schedules"] as schedule>
                        <th class="for-clear date" data-schedule-id="${schedule.id}">${schedule.date?substring(8,10)}/${schedule.date?substring(5,7)}<br>${schedule.date?substring(0,4)}</th>
                    </#list>
                </tr>

                <#items as pupil>
                    <tr data-pupil-id="${pupil.id}">
                        <td class="number">${pupil?index + 1}</td>
                        <td class="fullname">${pupil.lastName} ${pupil.firstName}</td>
                        <#list model["schedules"] as schedule>

                            <#assign attendanceExists = false>
                            <#list model["attendances"] as attendance>
                                <#if attendance.pupilId == pupil.id && attendance.scheduleId == schedule.id>
                                    <#assign attendanceExists = true>
                                    <#assign att = attendance>
                                </#if>
                            </#list>

                            <td class="for-clear attendance" data-pupil-id="${pupil.id}" data-schedule-id="${schedule.id}">
                                <#if attendanceExists == true>
                                    <div id="div-attendance-${pupil.id}-${schedule.id}" data-attendance-id="${att.id}"><#if att.grade == 0>-<#else>${att.grade}</#if></div>
                                    <input class="form-control" maxlength="2" id="input-attendance-${pupil.id}-${schedule.id}" data-attendance-id="${att.id}" data-in-database="1" value="<#if att.grade == 0><#else>${att.grade}</#if>" type="hidden">
                                <#else>
                                    <div id="div-attendance-${pupil.id}-${schedule.id}" data-attendance-id="">-</div>
                                    <input class="form-control" maxlength="2" id="input-attendance-${pupil.id}-${schedule.id}" data-attendance-id="" data-in-database="0" value="" type="hidden">
                                </#if>
                            </td>

                        </#list>
                    </tr>
                </#items>

            </#list>
            </table>

            <br>
    </div>
    </div>
</div>

<script>
    $(function() {

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
            var string_value = $(this).val();
            var int_value = parseInt($(this).val(), 10);
            console.log("int_value: " + int_value);

            if (string_value) {
                if (int_value < -1 || int_value > 12 || isNaN(int_value)) {
                    return;
                }
            }

            selectedTd.find("input").attr("value", (!string_value ? "" : int_value));
            selectedTd.find("div").text((!string_value ? "-" : int_value));
            hideInputShowDiv();
        });

        $("input").keyup(function(e){
            if (e.keyCode === 13) {
                $(this).trigger("enterKey");
            }
        });

        $(document).keyup(function(e){
            if (e.keyCode === 27) {
                hideInputShowDiv();
            }
        });

    });
</script>

<@h.footer/>
