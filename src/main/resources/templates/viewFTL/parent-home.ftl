<#import "header.ftl" as h>

<#assign pagetitle><@spring.message "parent.page.title"/></#assign>

<@h.header
pagetitle = "${pagetitle}"
cssSources = [
"//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css",
"/scripts/parent-home.css"
]
jsSources = [
"https://code.jquery.com/ui/1.12.1/jquery-ui.js",
"/scripts/datepicker/datepicker-en.js",
"/scripts/datepicker/datepicker-ru.js",
"/scripts/datepicker/datepicker-uk.js",
"/scripts/parent-home.js"
]/>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav"></div>
        <div class="col-sm-8 text-left">

            <br>
            <div id="datepicker" class="float-right"></div>
            <input type="hidden" id="locale" value="${.locale}">
            <h1>${pagetitle}</h1>

            <br>
            <p><@spring.message "parent.page.description"/></p>

            <br><br>
            <h5><@spring.message "parent.page.selectpupil"/></h5>
            <ul class="nav nav-pills" id="pupil-select" role="tablist">
            <#assign i=0>
            <#list model["pupilList"] as pupil>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#pupil${pupil.id}" role="tab" aria-controls="pupil${pupil.id}" data-pupil-id="${pupil.id}" data-pupil-form-id="${pupil.formId}">${pupil.firstName} ${pupil.lastName} [${pupil.formName}]</a>
                </li>
                <#assign i++>
            <#else>
                <@spring.message "parent.page.havenotpupils"/>
            </#list>
            </ul>

            <div class="clearfix"></div>

            <ul class="nav nav-tabs" id="data-type-select" role="tablist">
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#week-schedule" role="tab"><@spring.message "parent.page.schedule.title"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#attendance" role="tab"><@spring.message "parent.page.attendance.title"/></a>
                </li>
            </ul>

            <br>
            <div class="tab-content">
                <div class="tab-pane" id="week-schedule" role="tabpanel">

                    <#list 1..7 as dayOfWeek>
                        <div class="day-schedule">
                            <table id="day${dayOfWeek}" class="table table-striped">
                                <thead>
                                <tr>
                                    <th colspan="5"></th>
                                </tr>
                                <tr>
                                    <th style="width: 2%;"><@spring.message "schedule.lesson.position"/></th>
                                    <th style="width: 40%;"><@spring.message "schedule.subject"/></th>
                                    <th style="width: 18%;"><@spring.message "schedule.classroom"/></th>
                                    <th style="width: 40%;"><@spring.message "schedule.teacher"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                    <#list 1..10 as lessonPosition>
                                    <tr>
                                        <td>${lessonPosition}</td>
                                        <td class="for-clear"></td>
                                        <td class="for-clear"></td>
                                        <td class="for-clear"></td>
                                    </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>
                    </#list>

                </div>
                <div class="tab-pane active" id="attendance" role="tabpanel">

                    <div class="form-group">
                        <label for="lessons">Choose subject:</label>
                        <select class="form-control" id="lessons"></select>
                    </div>
                    <table id="attendanceData" class="table table-striped">
                        <thead>
                        <tr>
                            <th style="width: 50%;">Date</th>
                            <th style="width: 50%;">Mark</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>

                </div>
            </div>

        </div>
        <div class="col-sm-2 sidenav"></div>
    </div>
</div>

<@h.footer/>
