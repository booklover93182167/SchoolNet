<#import "header.ftl" as h>

<@h.header>

</@h.header>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav"></div>
        <div class="col-sm-8 text-left">
            <h1><@spring.message "parent.page.title"/></h1>
            <p>
                <@spring.message "pupil.list"/>
                <ul class="nav nav-pills" id="myTab" role="tablist">
                    <#list model["pupilList"] as pupil>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#pupil${pupil.id}" role="tab" aria-controls="pupil${pupil.id}">${pupil.firstName} ${pupil.lastName} [${pupil.formName}]</a>
                        </li>
                    <#else>
                        You haven't children
                    </#list>
                </ul>
                <div class="tab-content">
                    <#list model["pupilList"] as pupil>
                        <div class="tab-pane" id="pupil${pupil.id}" role="tabpanel">
                            <table class="table table-striped">
                                <tr>
                                    <th><@spring.message "schedule.date"/></th>
                                    <th><@spring.message "schedule.lesson.position"/></th>
                                    <th><@spring.message "schedule.subject"/></th>
                                    <th><@spring.message "schedule.classroom"/></th>
                                    <th><@spring.message "schedule.teacher"/></th>
                                    <th><@spring.message "schedule.homework"/></th>
                                </tr>
                                <#--<#assign modelname="schedule" + ${pupil.id}>-->
                                <#list model["schedule"+pupil.id] as schedule>
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
                    </#list>
                </div>
                <script>
                    $(function () {
                        $('#myTab a:first').tab('show')
                    })
                </script>
            </p>
        </div>
        <div class="col-sm-2 sidenav"></div>
    </div>
</div>

<@h.footer>

</@h.footer>
