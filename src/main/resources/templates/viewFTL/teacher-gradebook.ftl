<#import "header.ftl" as h>

<#assign pagetitle><@spring.message "teacher.gradebook.page.title"/></#assign>

<@h.header
pagetitle = "${pagetitle}"
/>

<style>
.gradebook tr th {
    vertical-align: middle;
}
.gradebook tr th.number {
    width: 2%;
}
.gradebook tr th.fullname {
}
.gradebook tr th.date {
    width: 6%;
}
</style>

<div class="container">
    <div class="row content">
        <div class="col-sm-12 text-left">


            <br>
            <h1>${pagetitle} ${model["teacher"]}</h1>

            <br>
            <p><@spring.message "teacher.gradebook.description"/></p>

            <#list model["formsAndLessons"]>
            <ul class="nav nav-pills" id="forms-lessons">
                <#items as formAndLesson>
                <li class="nav-item">
                    <a class="nav-link <#if model["formId"] == formAndLesson.formId && model["lessonId"] == formAndLesson.lessonId>active</#if>" href="/freemarker/teacher-gradebook/${formAndLesson.formId}/${formAndLesson.lessonId}">${formAndLesson.formName} ${formAndLesson.lessonName}</a>
                </li>
                </#items>
            </ul>
            </#list>

            <br>
            <table class="table table-striped gradebook">
            <#list model["pupils"]>

                <tr>
                    <th class="number">#</th>
                    <th class="fullname">Full Name</th>
                    <#list 0..9 as tableCol>
                        <th class="for-clear date" data-schedule-id="">0${tableCol?index + 1}/06<br>Пн</th>
                    </#list>
                </tr>

                <#items as pupil>
                    <tr data-pupil-id="${pupil.id}">
                        <#list 0..11 as tableCol>
                            <#if tableCol == 0>
                                <td class="number">${pupil?index + 1}</td>
                            <#elseif tableCol == 1>
                                <td class="fullname">${pupil.lastName} ${pupil.firstName}</td>
                            <#else>
                                <td class="for-clear attendance">12</td>
                            </#if>
                        </#list>
                    </tr>
                </#items>
            </#list>
            </table>

            <ul>
                <#list model["lessons"] as lesson>
                    <li>${lesson.date}</li>
                </#list>
            </ul>


            <br>
    </div>
    </div>
</div>

<script>
    $(function() {
//        $("#forms-lessons a:first").addClass("active");
    });
</script>

<@h.footer/>
