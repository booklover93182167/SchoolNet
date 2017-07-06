<#import "header.ftl" as h>

<#assign pagetitle><@spring.message "teacher.gradebook.page.title"/><#if model["course"]??> ${model["course"].formName}, ${model["course"].lessonName}</#if></#assign>

<@h.header
pagetitle = "${pagetitle}"
cssSources = [
"/scripts/teacher-gradebook.css"
]
jsSources = [
"/scripts/teacher-gradebook.js"
]/>

<div class="container">
    <div class="row content">
        <div class="col-sm-12 text-left">

            <br>
            <h1>${pagetitle}</h1>
            <div><@spring.message "teacher.gradebook.logged.as"/> <strong>${model["teacher"]}</strong></div>

            <br>
            <p><@spring.message "teacher.gradebook.description"/></p>

            <#list model["courses"]>
            <ul class="nav nav-pills">
                <#items as course>
                <li class="nav-item">
                    <a class="nav-link <#if model["course"].id == course.id>active</#if>" href="/freemarker/teacher-gradebook/${course.id}?size=${model["sizes"]}">${course.formName}, ${course.lessonName}</a>
                </li>
                </#items>
            </ul>
            </#list>

            <br>
            <#if model["course"]??>

                <table class="table table-striped gradebook">
                <#list model["pupils"]>

                    <tr>
                        <th class="number"><@spring.message "teacher.gradebook.table.pupil.position"/></th>
                        <th class="fullname"><@spring.message "teacher.gradebook.table.pupil.name"/></th>
                        <#list model["schedules"] as schedule>
                            <th class="for-clear date lesson-type-${schedule.lessonTypeId}" title="${schedule.lessonTypeName}" data-schedule-id="${schedule.id}">${schedule.date?substring(8,10)}/${schedule.date?substring(5,7)}</th>
                        </#list>
                    </tr>

                    <#items as pupil>
                        <tr data-pupil-id="${pupil.id}">
                            <td class="number">${pupil?index + 1}</td>
                            <td class="fullname">${pupil.lastName} ${pupil.firstName}</td>
                            <#list model["schedules"] as schedule>

                                <#assign attendanceExists = false>
                                <#list model["attendances"] as attendance>
                                    <#if attendance.pupilId == pupil.id && attendance.scheduleId == schedule.id>
                                        <#assign attendanceExists = true>
                                        <#assign att = attendance>
                                        <#break>
                                    </#if>
                                </#list>

                                    <#if attendanceExists == true>
                                        <td class="for-clear attendance lesson-type-${schedule.lessonTypeId}" data-pupil-id="${pupil.id}" data-schedule-id="${schedule.id}" data-attendance-id="${att.id}">
                                            <div id="div-attendance-${pupil.id}-${schedule.id}"><#if !att.grade??>-<#elseif att.grade == 0>N<#else>${att.grade}</#if></div>
                                            <input class="form-control" maxlength="2" id="input-attendance-${pupil.id}-${schedule.id}" value="<#if att.grade??>${att.grade}</#if>" type="hidden">
                                        </td>
                                    <#else>
                                        <td class="for-clear attendance lesson-type-${schedule.lessonTypeId}" data-pupil-id="${pupil.id}" data-schedule-id="${schedule.id}" data-attendance-id="-1">
                                            <div id="div-attendance-${pupil.id}-${schedule.id}">-</div>
                                            <input class="form-control" maxlength="2" id="input-attendance-${pupil.id}-${schedule.id}" value="" type="hidden">
                                        </td>
                                    </#if>

                            </#list>
                        </tr>
                    </#items>

                </#list>
                </table>

                <form id="sizeChangerForm" action="" method="get">
                <div class="form-group float-right">
                    <select class="form-control" name="size" id="sizeSelector">
                        <#list [1, 5, 10, 15, 20] as s>
                            <#if model["sizes"] == s>
                                <option value="${s}" selected="selected">${s}</option>
                            <#else>
                                <option value="${s}">${s}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
                </form>

                <nav aria-label="Page navigation">
                    <ul class="pagination justify-content-center">
                        <li class="page-item<#if model["current"] == 0> disabled</#if>"><a class="page-link" href="?page=0&size=${model["sizes"]}"><@spring.message "pagination.first"/></a></li>
                        <li class="page-item<#if model["current"] == 0> disabled</#if>"><a class="page-link" href="?page=${model["current"]-1}&size=${model["sizes"]}"><@spring.message "pagination.prev"/></a></li>
                        <#list 0..model["longs"]-1 as i>
                            <#if model["current"] != i>
                                <li class="page-item"><a class="page-link" href="?page=${i}&size=${model["sizes"]}">${i+1}</a></li>
                            <#else>
                                <li class="page-item active"><span class="page-link">${i+1}</span></li>
                            </#if>
                        </#list>
                        <li class="page-item<#if model["current"] == model["longs"]-1> disabled</#if>"><a class="page-link" href="?page=${model["current"]+1}&size=${model["sizes"]}"><@spring.message "pagination.next"/></a></li>
                        <li class="page-item<#if model["current"] == model["longs"]-1> disabled</#if>"><a class="page-link" href="?page=${model["longs"]-1}&size=${model["sizes"]}"><@spring.message "pagination.last"/></a></li>
                    </ul>
                </nav>

            <#else>

                <div class="alert alert-danger" role="alert">
                    <h4 class="alert-heading"><@spring.message "teacher.gradebook.error"/></h4>
                    <@spring.message "teacher.gradebook.error.description"/>
                </div>

            </#if>

        </div>
    </div>
</div>

<@h.footer/>
