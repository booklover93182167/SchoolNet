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
    "/scripts/datepicker/datepicker-uk.js"
    ]/>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav"></div>
        <div class="col-sm-8 text-left">

            <br>
            <div id="datepicker" class="float-right"></div>
            <h1><@spring.message "parent.page.title"/></h1>

            <br>
            <p><@spring.message "parent.page.description"/></p>

            <br><br>
            <h5><@spring.message "parent.page.selectpupil"/></h5>
            <ul class="nav nav-pills" id="myTab" role="tablist">
                <#assign i=0>
                <#list model["pupilList"] as pupil>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#pupil${pupil.id}" role="tab" aria-controls="pupil${pupil.id}" data-pupil-form-id="${pupil.formId}">${pupil.firstName} ${pupil.lastName} [${pupil.formName}]</a>
                    </li>
                    <#assign i++>
                <#else>
                    <@spring.message "parent.page.havenotpupils"/>
                </#list>
            </ul>

            <div class="clearfix"></div>

            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" data-toggle="tab" href="#week-schedule" role="tab"><@spring.message "parent.page.schedule.title"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="tab" href="#attendance" role="tab"><@spring.message "parent.page.attendance.title"/></a>
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


<script>
    // Localization Fix
    window.lang = "${.locale}";
    window.lessonPosition = "<@spring.message "schedule.lesson.position"/>";
    window.subject = "<@spring.message "schedule.subject"/>";
    window.classroom = "<@spring.message "schedule.classroom"/>";
    window.teacher = "<@spring.message "schedule.teacher"/>";
    window.homework = "<@spring.message "schedule.homework"/>";
</script>
<script type="text/javascript" src="/scripts/parent-home.js"></script>

<@h.footer/>
