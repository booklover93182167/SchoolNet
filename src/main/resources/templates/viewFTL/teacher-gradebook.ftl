<#import "header.ftl" as h>

<#assign pagetitle><@spring.message "teacher.gradebook.page.title"/></#assign>

<@h.header
pagetitle = "${pagetitle}"
/>

<div class="container">
    <div class="row content">
        <div class="col-sm-12 text-left">


            <br>
            <h1>${pagetitle}</h1>

            <br>
            <p><@spring.message "teacher.gradebook.description"/></p>

            Teacher ID: ${model["teacherId"]}

            <table>
                <tr>
                    <td>Form</td>
                    <td>Lesson</td>
                </tr>
            <#list model["formsAndLessons"] as formsAndLessons>
                <tr>
                    <td>${formsAndLessons.formName}</td>
                    <td>${formsAndLessons.lessonName}</td>
                </tr>
            </#list>
            </table>

            <br>
    </div>
    </div>
</div>

<@h.footer/>
