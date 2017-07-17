<#import "../header.ftl" as h>
<@h.header>

</@h.header>
<link rel="stylesheet" type="text/css" href="/scripts/admin-home-popup.css">
<br>
<div id="header">
    <h2>
        <div style=" position: absolute;">
            <p class="text-right" style="font-style: italic"><@spring.message "hello"/>
                , ${model.currentUser.firstName} ${model.currentUser.lastName}</p></div>
        <br>
    <@spring.message "teacher.class"/> ${model.formName}
        <button type="button" class="btn btn-primary btn-lg" style="margin-left: 84%;"
                onclick="window.location.href='/freemarker/teacher-my-class/newPupil/${model.currentUser.formId}'">
        <@spring.message "pupil.add"/>
        </button>

    </h2>
</div>
<table class="table table-hover">
    <thead>
    <tr>
        <th><@spring.message "teacher.gradebook.table.pupil.name"/></th>
        <th><@spring.message "email"/></th>
        <th><@spring.message "parents"/></th>

    </tr>
    </thead>

    <tbody>

    <#list model.pupils as i>
    <tr>
        <td><p style="text-shadow:5px 5px 15px grey;">${i.lastName } ${i.firstName }</p></td>
        <td>
            ${i.email}
        </td>
        <td>
            <#if i.parents??>
                <#list i.parents as k>
                    <p style="text-shadow:5px 5px 10px grey;margin-bottom: 0px;margin-top: 10px;"> ${k.firstName } ${k.lastName}
                        <br></p>
                    <i class="fa fa-envelope-open-o" aria-hidden="true">
                    ${k.email} </i><br>
                </#list>
            </#if>
            <button type="button" class="btn btn-outline-info btn-sm"
                    style="margin-top: 10px;"><@spring.message "addParent"/></button>
        </td>
    </tr>
    </#list>


    </tbody>
</table>
<@h.footer>

</@h.footer>
