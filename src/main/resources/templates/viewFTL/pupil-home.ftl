<#import "header.ftl" as h>
<@h.header></@h.header>

<link rel='stylesheet' href='/scripts/calendar/css/jquery-ui.css'/>
<link rel='stylesheet' href='/scripts/calendar/fc/fullcalendar.css'/>
<link rel='stylesheet' href='/scripts/calendar/css/style.css'/>

<div class="container-fluid">
    <div class="page-header">
        <h1>Welcome ${model.pupilFirstName} ${model.pupilLastName}</h1>
        <br>
        <h3> My schedule on <span id="label_date"></span></h3>
    </div>
    <div class="row">
        <div class="col-md-7">
            <table class="table table-striped">
                <tr>
                    <th>Position</th>
                    <th>Lesson</th>
                    <th>Homework</th>
                    <th>Classroom</th>
                    <th>Teacher</th>
                    <th>Attendance</th>
                </tr>
                <tbody id="scheduleTable">
                <#list 1..10 as table_row>
                <tr id="row">
                    <#list 0..5 as col>
                        <#if col == 0>
                            <td>${table_row}</td>
                        <#elseif col == 2>
                            <td class="homework">-</td>
                            <div class="container">
                                <div class="modal fade .modal-sm" id="modal-homework">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;
                                                </button>
                                                <h3 class="modal-title">This is your homework</h3>
                                            </div>
                                            <div class="modal-body" id="setHomework"></div>
                                            <div class="modal-footer">
                                                <a href="" class="btn btn-default" data-dismiss="modal">Close</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <#elseif col == 4>
                            <td class="teacher">-</td>
                            <div class="container">
                                <div class="modal fade .modal-sm" id="modal-teacher">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;
                                                </button>
                                                <h3 class="modal-title">Your teacher</h3>
                                            </div>
                                            <div class="modal-body" id="teacher-modal"></div>
                                            <div class="modal-footer">
                                                <a href="" class="btn btn-default" data-dismiss="modal">Close</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <#else>
                            <td id="clear">-</td>
                        </#if>
                    </#list>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="col-md-5">
            <div id='calendar'></div>
        </div>
    </div>
</div>

<script src="http://code.jquery.com/jquery.min.js"></script>
<script src="/scripts/calendar/js/jquery-ui.js"></script>
<script src="/scripts/calendar/fc/lib/moment.min.js"></script>
<script src="/scripts/calendar/fc/fullcalendar.js"></script>
<script src="/scripts/calendar/fc/locale-all.js"></script>
<script src="/scripts/calendar/js/main.js"></script>

<@h.footer></@h.footer>
