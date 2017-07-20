<#import "header.ftl" as h>

<#assign pagetitle><@spring.message "attendance-log.page.title"/></#assign>

<@h.header
pagetitle = "${pagetitle}"
/>

<div class="container-fluid">

    <br>
    <h1>${pagetitle}</h1>
    <div><@spring.message "teacher.gradebook.logged.as"/> <strong>${model.teacher.firstName} ${model.teacher.lastName}</strong></div>

    <br>

    <table class="table table-striped">
        <tr>
            <th><@spring.message "attendance.date"/></th>
            <th><@spring.message "attendance.grade.old"/></th>
            <th><@spring.message "attendance.grade.new"/></th>
            <#--<th><@spring.message "attendance.reason"/></th>-->
            <th><@spring.message "attendance.teacher"/></th>
            <th><@spring.message "attendance.pupil"/></th>
            <th><@spring.message "attendance.form"/></th>
            <th><@spring.message "attendance.lesson"/></th>
        </tr>
    <#list model.log as log>
        <tr>
            <td>${log.date.format('dd.MM.yyyy HH:mm:ss')}</td>
            <td><#if log.oldGrade??>${log.oldGrade}<#else>-</#if></td>
            <td><#if log.newGrade??>${log.newGrade}<#else>-</#if></td>
            <#--<td>${log.reason}</td>-->
            <td>${log.teacherFirstName} ${log.teacherLastName}</td>
            <td>${log.pupilFirstName} ${log.pupilLastName}</td>
            <td>${log.formName}</td>
            <td>${log.lessonName}</td>
        </tr>
    </#list>
    </table>

    <br>

</div>

<@h.footer/>
