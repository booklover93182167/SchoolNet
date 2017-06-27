<#import "header.ftl" as h>

<#assign pagetitle><@spring.message "teacher.gradebook.page.title"/><#if model["formAndLesson"]??> ${model["formAndLesson"].formName}, ${model["formAndLesson"].lessonName}</#if></#assign>

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
            <h1>${pagetitle}</h1>
            <div><@spring.message "teacher.gradebook.logged.as"/> <strong>${model["teacher"]}</strong></div>

            <br>
            <p><@spring.message "teacher.gradebook.description"/></p>

            <#list model["formsAndLessons"]>
            <ul class="nav nav-pills" id="forms-lessons">
                <#items as formAndLesson>
                <li class="nav-item">
                    <a class="nav-link <#if model["formAndLesson"]??><#if model["formAndLesson"].formId == formAndLesson.formId && model["formAndLesson"].lessonId == formAndLesson.lessonId>active</#if></#if>" href="/freemarker/teacher-gradebook/${formAndLesson.formId}/${formAndLesson.lessonId}?size=${model["sizes"]}">${formAndLesson.formName}, ${formAndLesson.lessonName}</a>
                </li>
                </#items>
            </ul>
            </#list>

            <br>
            <#if model["formAndLesson"]??>

                <table class="table table-striped gradebook">
                <#list model["pupils"]>

                    <tr>
                        <th class="number"><@spring.message "teacher.gradebook.table.pupil.position"/></th>
                        <th class="fullname"><@spring.message "teacher.gradebook.table.pupil.name"/></th>
                        <#list model["schedules"] as schedule>
                            <th class="for-clear date" data-schedule-id="${schedule.id}">${schedule.date?substring(8,10)}/${schedule.date?substring(5,7)}</th>
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
                                        <#break>
                                    </#if>
                                </#list>

                                    <#if attendanceExists == true>
                                        <td class="for-clear attendance" data-pupil-id="${pupil.id}" data-schedule-id="${schedule.id}" data-attendance-id="${att.id}">
                                            <div id="div-attendance-${pupil.id}-${schedule.id}" data-attendance-id="${att.id}"><#if !att.grade??>-<#elseif att.grade == 0>N<#else>${att.grade}</#if></div>
                                            <input class="form-control" maxlength="2" id="input-attendance-${pupil.id}-${schedule.id}" value="<#if att.grade??>${att.grade}</#if>" type="hidden">
                                        </td>
                                    <#else>
                                        <td class="for-clear attendance" data-pupil-id="${pupil.id}" data-schedule-id="${schedule.id}" data-attendance-id="-1">
                                            <div id="div-attendance-${pupil.id}-${schedule.id}" data-attendance-id="">-</div>
                                            <input class="form-control" maxlength="2" id="input-attendance-${pupil.id}-${schedule.id}" value="" type="hidden">
                                        </td>
                                    </#if>

                            </#list>
                        </tr>
                    </#items>

                </#list>
                </table>

                <form id="sizeChangerForm" action="" method="get">
                <div class="form-group float-right">
                    <select class="form-control" name="size" id="sizeSelector">
                        <#list [1, 5, 10, 15, 20] as s>
                            <#if model["sizes"] == s>
                                <option value="${s}" selected="selected">${s}</option>
                            <#else>
                                <option value="${s}">${s}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
                </form>

                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <li class="page-item<#if model["current"] == 0> disabled</#if>"><a class="page-link" href="?page=0&size=${model["sizes"]}">First</a></li>
                        <li class="page-item<#if model["current"] == 0> disabled</#if>"><a class="page-link" href="?page=${model["current"]-1}&size=${model["sizes"]}">Previous</a></li>
                        <#list 0..model["longs"]-1 as i>
                            <#if model["current"] != i>
                                <li class="page-item"><a class="page-link" href="?page=${i}&size=${model["sizes"]}">${i+1}</a></li>
                            <#else>
                                <li class="page-item active"><span class="page-link">${i+1}</span></li>
                            </#if>
                        </#list>
                        <li class="page-item<#if model["current"] == model["longs"]-1> disabled</#if>"><a class="page-link" href="?page=${model["current"]+1}&size=${model["sizes"]}">Next</a></li>
                        <li class="page-item<#if model["current"] == model["longs"]-1> disabled</#if>"><a class="page-link" href="?page=${model["longs"]-1}&size=${model["sizes"]}">Last</a></li>
                    </ul>
                </nav>

            <#else>

                <div class="alert alert-danger" role="alert">
                    <h4 class="alert-heading"><@spring.message "teacher.gradebook.error"/></h4>
                    <@spring.message "teacher.gradebook.error.description"/>
                </div>

            </#if>

        </div>
    </div>
</div>

<script>
    $(function() {

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
            var string_value = $(this).val();
            var int_value = parseInt($(this).val(), 10);
            console.log("int_value: " + int_value);

            if (string_value) {
                if (int_value < 0 || int_value > 12 || isNaN(int_value)) {
                    alert("Invalid grade!");
                    return;
                }
            }

            var id = selectedTd.data("attendance-id");
            var grade = int_value;
            var enabled = true;
            var pupilId = selectedTd.data("pupil-id");
            var scheduleId = selectedTd.data("schedule-id");
            var attendanceDTO;

            if (id == -1) {
                attendanceDTO = {
                    grade: grade,
                    enabled: enabled,
                    pupilId: pupilId,
                    scheduleId: scheduleId
                };
            } else {
                attendanceDTO = {
                    id : id,
                    grade: grade,
                    enabled: enabled,
                    pupilId: pupilId,
                    scheduleId: scheduleId
                };
            }
            $.ajax({
                url : "/freemarker/teacher-gradebook/update",
                type : "POST",
                contentType : "application/json",
                data : JSON.stringify(attendanceDTO),
                success : function (response) {
                    selectedTd.find("input").attr("value", (!string_value ? "" : response.grade));
                    selectedTd.find("div").text((!string_value ? "-" : response.grade));
                    hideInputShowDiv();
                }
            });
        });

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
</script>

<@h.footer/>
