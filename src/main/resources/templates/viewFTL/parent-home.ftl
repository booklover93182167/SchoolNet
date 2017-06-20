<#import "header.ftl" as h>

<#assign pagetitle><@spring.message "parent.page.title"/></#assign>

<@h.header
    pagetitle = "${pagetitle}"
    cssSources = [
    "//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"
    ]
    jsSources = [
    "https://code.jquery.com/ui/1.12.1/jquery-ui.js",
    "/scripts/datepicker/datepicker-en.js",
    "/scripts/datepicker/datepicker-ru.js",
    "/scripts/datepicker/datepicker-uk.js",
    "/scripts/parent-page.js"
    ]>
</@h.header>

<style>
    .daytable {
        float: left;
        width: 33%;
    }
    .daytable .table td,
    .daytable .table th {
        padding: 1px 2px;
        text-align: center;
        white-space: nowrap;
    }
</style>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav"></div>
        <div class="col-sm-8 text-left">
            <br>
            <div id="datepicker" style="float: right;"></div>
            <h1><@spring.message "parent.page.title"/></h1>

            <br>
            <p>Тут буде опис</p>

            <br>
            <br>
            <br><@spring.message "parent.selectpupil"/>
            <br><br>
            <ul class="nav nav-pills" id="myTab" role="tablist">
                <#assign i=0>
                <#list model["pupilList"] as pupil>
                    <li class="nav-item">
                        <a class="nav-link <#if i==0>active</#if>" data-toggle="tab" href="#pupil${pupil.id}" role="tab" aria-controls="pupil${pupil.id}" data-pupil-form-id="${pupil.formId}">${pupil.firstName} ${pupil.lastName} [${pupil.formName}]</a>
                    </li>
                    <#if i==0><input type="hidden" id="pupilFormId" value="${pupil.formId}"></#if>
                    <#assign i++>
                <#else>
                    <@spring.message "parent.havenotpupils"/>
                </#list>
            </ul>

            <div style="clear: both"></div>

            <br>
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#schedule" role="tab"><@spring.message "schedule.title"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#attendance" role="tab"><@spring.message "attendance.title"/></a>
                </li>
            </ul>


            <#-- Datepicker with input field -->

            <#--<div class="input-group">-->
                <#--<input type="text" class="form-control" id="datepicker" placeholder="<@spring.message 'date.select'/>"></p>-->
                <#--<span class="input-group-btn">-->
                    <#--<button type="button" class="btn btn-secondary">-->
                        <#--<i class="fa fa-calendar" aria-hidden="true"></i>-->
                    <#--</button>-->
                <#--</span>-->
            <#--</div>-->


            <#-- Ajax table generator -->

            <#--<table id="scheduletable" class="table table-striped">-->
                <#--<thead>-->
                    <#--<tr>-->
                        <#--<th><@spring.message "schedule.date"/></th>-->
                        <#--<th><@spring.message "schedule.lesson.position"/></th>-->
                        <#--<th><@spring.message "schedule.subject"/></th>-->
                        <#--<th><@spring.message "schedule.classroom"/></th>-->
                        <#--<th><@spring.message "schedule.teacher"/></th>-->
                        <#--<th><@spring.message "schedule.homework"/></th>-->
                    <#--</tr>-->
                <#--</thead>-->
                <#--<tbody></tbody>-->
            <#--</table>-->



            <#-- FreeMarker table generator -->

            <#--<div class="tab-content">-->
            <#--<#assign i=0>-->
            <#--<#list model["pupilList"] as pupil>-->
                <#--<div class="tab-pane <#if i==0>active</#if>" id="pupil${pupil.id}" role="tabpanel">-->
                    <#--<table class="table table-striped">-->
                        <#--<tr>-->
                            <#--<th><@spring.message "schedule.date"/></th>-->
                            <#--<th><@spring.message "schedule.lesson.position"/></th>-->
                            <#--<th><@spring.message "schedule.subject"/></th>-->
                            <#--<th><@spring.message "schedule.classroom"/></th>-->
                            <#--<th><@spring.message "schedule.teacher"/></th>-->
                            <#--<th><@spring.message "schedule.homework"/></th>-->
                        <#--</tr>-->
                        <#--<#list model["schedule" + pupil.id] as schedule>-->
                            <#--<tr>-->
                                <#--<td>${schedule.date}</td>-->
                                <#--<td>${schedule.lessonPosition}</td>-->
                                <#--<td>${schedule.lessonName}</td>-->
                                <#--<td>${schedule.classroomName}</td>-->
                                <#--<td>${schedule.teacherFirstName} ${schedule.teacherLastName}</td>-->
                                <#--<td>${schedule.homework}</td>-->
                            <#--</tr>-->
                        <#--</#list>-->
                    <#--</table>-->
                <#--</div>-->
                <#--<#assign i++>-->
            <#--</#list>-->
            <#--</div>-->


            <br>

            <div class="tab-content">
                <div class="tab-pane active" id="schedule" role="tabpanel">

                    <div id="schedule-wrapper"></div>

                </div>
                <div class="tab-pane" id="attendance" role="tabpanel">

                    Soon will be implemented

                </div>
            </div>

            <#--<script>-->
                <#--$(function () {-->
                    <#--$('#myTab a:first').tab('show')-->
                <#--})-->
            <#--</script>-->
        </div>
        <div class="col-sm-2 sidenav"></div>
    </div>
</div>

<@h.footer>

</@h.footer>
