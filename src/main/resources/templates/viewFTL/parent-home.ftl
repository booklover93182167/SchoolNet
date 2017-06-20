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
    "/scripts/datepicker/datepicker-uk.js"
    ]>
</@h.header>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav"></div>
        <div class="col-sm-8 text-left">
            <h1><@spring.message "parent.page.title"/></h1>
            <@spring.message "parent.selectpupil"/>

            <br>
            <br>
            <ul class="nav nav-pills" id="myTab" role="tablist">
                <#assign i=0>
                <#list model["pupilList"] as pupil>
                    <li class="nav-item">
                        <a class="nav-link <#if i==0>active</#if>" data-toggle="tab" href="#pupil${pupil.id}" role="tab" aria-controls="pupil${pupil.id}">${pupil.firstName} ${pupil.lastName} [${pupil.formName}]</a>
                    </li>
                    <#assign i++>
                <#else>
                    <@spring.message "parent.havenotpupils"/>
                </#list>
            </ul>

            <br>
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#schedule" role="tab"><@spring.message "schedule.title"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#attendance" role="tab"><@spring.message "attendance.title"/></a>
                </li>
            </ul>


            <br>
            <div class="input-group">
                <input type="text" class="form-control" id="datepicker" placeholder="<@spring.message 'date.select'/>"></p>
                <span class="input-group-btn">
                    <button type="button" class="btn btn-secondary">
                        <i class="fa fa-calendar" aria-hidden="true"></i>
                    </button>
                </span>
            </div>

            <script>
                $(function() {
                    var today = new Date();
                    var minYear = today.getFullYear();
                    var maxYear = today.getFullYear();
                    if (today.getMonth() < 9 - 1) {
                        minYear -= 1;
                    } else {
                        maxYear += 1;
                    }
                    $.datepicker.setDefaults($.datepicker.regional["en"]);
                    $("#datepicker").datepicker({
                        // defaultDate: new Date(),
                        dateFormat: "dd.mm.yy",
                        minDate: new Date(minYear, 9 - 1, 1),
                        maxDate: new Date(maxYear, 8 - 1, 31),
                        showButtonPanel: true
                    });
                    $("#datepicker").datepicker("setDate", new Date());
                });
            </script>

            <br>
            <div class="tab-content">
                <div class="tab-pane active" id="schedule" role="tabpanel">

                    <div class="tab-content">
                    <#assign i=0>
                    <#list model["pupilList"] as pupil>
                        <div class="tab-pane <#if i==0>active</#if>" id="pupil${pupil.id}" role="tabpanel">
                            <table class="table table-striped">
                                <tr>
                                    <th><@spring.message "schedule.date"/></th>
                                    <th><@spring.message "schedule.lesson.position"/></th>
                                    <th><@spring.message "schedule.subject"/></th>
                                    <th><@spring.message "schedule.classroom"/></th>
                                    <th><@spring.message "schedule.teacher"/></th>
                                    <th><@spring.message "schedule.homework"/></th>
                                </tr>
                                <#list model["schedule" + pupil.id] as schedule>
                                    <tr>
                                        <td>${schedule.date}</td>
                                        <td>${schedule.lessonPosition}</td>
                                        <td>${schedule.lessonName}</td>
                                        <td>${schedule.classroomName}</td>
                                        <td>${schedule.teacherFirstName} ${schedule.teacherLastName}</td>
                                        <td>${schedule.homework}</td>
                                    </tr>
                                </#list>
                            </table>
                        </div>
                        <#assign i++>
                    </#list>
                    </div>

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
