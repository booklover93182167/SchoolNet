<#import "header.ftl" as h>
<@h.header>

</@h.header>

<link rel='stylesheet' href='/scripts/calendar/css/jquery-ui.css'/>
<link rel='stylesheet' href='/scripts/calendar/fc/fullcalendar.css'/>
<link rel='stylesheet' href='/scripts/calendar/css/style.css'/>

<div id="header">
    <h1>Welcome ${model.currentPupil.firstName} ${model.currentPupil.lastName}</h1>
    <br>
</div>

<div class="row">
    <div class="col-md-6" id="scheduleTable">
        <label> My schedule on <span id="label_date"></span></label>
        <table class="table table-striped">
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
    <div class="col-md-6">
        <div id='calendar'></div>
    </div>
</div>

<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="/scripts/calendar/js/jquery-ui.js"></script>
<script src="/scripts/calendar/fc/lib/moment.min.js"></script>
<script src="/scripts/calendar/fc/fullcalendar.js"></script>
<script src="/scripts/calendar/fc/locale-all.js"></script>
<script src="/scripts/calendar/js/main.js"></script>

<@h.footer>

</@h.footer>

