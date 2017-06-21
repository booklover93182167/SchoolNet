<#import "header.ftl" as h>

<#assign pagetitle><@spring.message "parent.page.title"/></#assign>

<@h.header
    pagetitle = "${pagetitle}"
    cssSources = [
    "/scripts/parent-home.css",
    "//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"
    ]
    jsSources = [
    "/scripts/parent-home.js",
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
                        <a class="nav-link" data-toggle="tab" href="#pupil${pupil.id}" role="tab" aria-controls="pupil${pupil.id}" data-pupil-form-id="${pupil.formId}">${pupil.firstName} ${pupil.lastName} [${pupil.formName}]</a>
                    </li>
                    <#assign i++>
                <#else>
                    <@spring.message "parent.havenotpupils"/>
                </#list>
            </ul>

            <div style="clear: both"></div>

            <br>
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#week-schedule" role="tab"><@spring.message "schedule.title"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#attendance" role="tab"><@spring.message "attendance.title"/></a>
                </li>
            </ul>

            <br>

            <div class="tab-content">
                <div class="tab-pane active" id="week-schedule" role="tabpanel"></div>
                <div class="tab-pane" id="attendance" role="tabpanel">Soon will be implemented</div>
            </div>

        </div>
        <div class="col-sm-2 sidenav"></div>
    </div>
</div>

<@h.footer>

</@h.footer>
