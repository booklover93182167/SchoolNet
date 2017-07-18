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
        <td><p style="text-shadow:5px 5px 15px grey;">${i.lastName } ${i.firstName }
            <button type="button" class="close" aria-label="Close" onclick="showDisableModal(${i.id})" title="delete">
                <span aria-hidden="true">&times;</span>
            </button>
        </p></td>
        <td>
            ${i.email}
        </td>
        <td>
            <#if i.parents??>
                <#list i.parents as k>
                    <p style="text-shadow:5px 5px 10px grey;margin-bottom: 0px;margin-top: 10px;"> ${k.firstName } ${k.lastName} <button type="button" class="close" aria-label="Close" title="delete">
                        <span aria-hidden="true">&times;</span>
                    </button>
                        <br></p>
                    <i class="fa fa-envelope-open-o" aria-hidden="true">
                    ${k.email} </i><br>
                </#list>
            </#if>
            <button type="button" class="btn btn-outline-info btn-sm"
                    style="margin-top: 10px;"><@spring.message "addParent"/></button>
        </td>

        <div class="deleteModal" id="delete${i.id}">
            <div class="modal-content2">
                <div class="modal-body">
                    <h2><@spring.message "pupil.delete"/> ${i.firstName} ${i.lastName} <@spring.message "pupil.fromClass"/></h2>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"
                            onclick="removeDisableModal(${i.id})">
                        <span class="fa fa-ban"></span>&nbsp;<span><@spring.message "school.cancel"/></span>
                    </button>
                    <button type="submit" class="btn btn-success"
                            onclick="window.location.href='/freemarker/teacher-my-class-delete/${i.id}'">
                        <span class="fa fa-check"></span><span><@spring.message "yes"/></span>
                    </button>
                </div>
            </div>
        </div>

    </tr>
    </#list>


    </tbody>
</table>
<#list model.unassignedPupils as k>
${k.firstName } ${k.lastName}
</#list>
<script src="/scripts/teacher-my-class.js"></script>
<@h.footer>

</@h.footer>
