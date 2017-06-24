<#import "header.ftl" as h>
<@h.header>

</@h.header>

<link rel='stylesheet' href='/scripts/calendar/css/jquery-ui.css'/>
<link rel='stylesheet' href='/scripts/calendar/fc/fullcalendar.css'/>
<link rel='stylesheet' href='/scripts/calendar/css/style.css'/>

<div id="header">
    <h1>Welcome ${model.pupilFirstName} ${model.pupilLastName}</h1>
    <br>
</div>

<div class="row">
    <div class="col-md-7" >
        <label> My schedule on <span id="label_date"></span></label>
        <table class="table table-striped">
            <tr>
                <th>Position</th>
                <th>Lesson</th>
                <th>Homework</th>
                <th>Classroom</th>
                <th>Teacher</th>
                <th>Attendance</th>
            </tr>
            <tbody id="scheduleTable"></tbody>
        </table>
    </div>
    <div class="col-md-5">
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

