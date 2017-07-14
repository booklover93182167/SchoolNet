<#import "../header.ftl" as h>
<@h.header>

</@h.header>
<link rel="stylesheet" type="text/css" href="/scripts/teacher-mgmt-popup.css">
<br>
<div id="header">
    <h2>
        <div style=" position: absolute;">
            <span><@spring.message "hello"/>, ${model.currentUser.firstName} ${model.currentUser.lastName}</span></div>
        <br>
        <span><@spring.message "teacher.class"/> ${model.formName} </span>
    </h2>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Учень</th>
        <th>Email</th>
        <th>Батьки</th>

    </tr>
    </thead>
    <tfoot>
    <tr>
        <th colspan="2">
            <button type="button" class="btn btn-outline-primary"
                    onclick="window.location.href='/freemarker/teacher-my-class/newPupil/${model.currentUser.formId}'">
                New pupil
            </button>
        </th>

    </tr>
    </tfoot>
    <tbody>

    <#list model.pupils as i>
    <tr>
        <td>${i.lastName } ${i.firstName }</td>
        <td>

        </td>
        <td>
            <#if i.parents??>
                <#list i.parents as k>
                ${k.firstName } ${k.lastName} <br></#list>
            </#if>
            <button type="button" class="btn btn-outline-info btn-sm">New parent</button>
        </td>
    </tr>
    </#list>


    </tbody>
</table>
<@h.footer>

</@h.footer>
