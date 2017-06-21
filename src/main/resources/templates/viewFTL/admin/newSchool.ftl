<#import "../header.ftl" as h>
<@h.header>

</@h.header>

<br>
<div id="header">
    <h2>
        <span>Create a new school</span>
    </h2>
</div>
<form name="editForm" action="/freemarker/admin-home/newSchool" method="post">
    <div class="modal-body">
        <div class="form-group">
            <label class="form-control-label">School name</label>
        <@spring.formInput "schoolDTO.name"/>
        <@spring.showErrors "schoolDTO.name","error" />
        <#if nameFail??>
            <span style="color:red">${nameFail}</span>
        </#if>
        </div>
        <@spring.message "school.enabled"/>:
        <select name="enabled">
            <option value="true"><@spring.message "true"/></option>
            <option value="false"><@spring.message "false"/></option>
        </select>

    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">
            <span class="fa fa-ban"></span>&nbsp;<span>Cancel</span>
        </button>
        <button type="submit" class="btn btn-primary">
            <span class="fa fa-save"></span>&nbsp;<span>Save</span>
        </button>
    </div>
</form>

<@h.footer>

</@h.footer>
