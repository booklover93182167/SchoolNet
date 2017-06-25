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
                            <td class="for-clear attendance" data-pupil-id="${pupil.id}" data-schedule-id="${schedule.id}">
                            <#list model["attendances"] as attendance>
                                <#if attendance.pupilId == pupil.id && attendance.scheduleId == schedule.id>
                                    ${attendance.grade}
                                </#if>
                            </#list>
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
//        $("#forms-lessons a:first").addClass("active");
    });
</script>

<@h.footer/>
