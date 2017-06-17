<#import "header.ftl" as h>
<@h.header>

</@h.header>


<div id="header">
    <h2></h2>
</div>
<div id="content">
    <table class="table table-striped">
        <tr>
            <th colspan="5">My schedule</th>
        </tr>
        <tr>
            <th>Position</th>
            <th>Lesson</th>
            <th>Homework</th>
            <th>Classroom</th>
            <th>Teacher</th>
        </tr>
    <#list model["mySchedule"] as schedule>
        <tr>
            <td>${schedule.lessonPosition}</td>
            <td>${schedule.lessonName}</td>
            <td>${schedule.homework}</td>
            <td>${schedule.classroomName}</td>
            <td>${schedule.teacherFirstName} ${schedule.teacherLastName}</td>
        </tr>
    </#list>
    </table>
</div>




<@h.footer>

</@h.footer>

