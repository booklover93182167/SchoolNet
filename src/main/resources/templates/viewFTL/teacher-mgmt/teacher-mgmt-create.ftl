<#import "../header.ftl" as h>
<@h.header>

</@h.header>

<br>
<div id="header">
    <h2>
        <span>Create a new Teacher</span>
    </h2>
</div>
<form name="create" action="/freemarker/teacher-mgmt/teacher-mgmt-create" method="post">
<div class="modal-body">
    <div class="form-group">
        <label class="form-control-label">First Name</label>
        <@spring.formInput "teacherDTO.firstName"/>
        <@spring.showErrors "teacherDTO.firstName","error" />
    </div>
    <div class="form-group">
        <label class="form-control-label">Last Name</label>
        <@spring.formInput "teacherDTO.lastName" />
        <@spring.showErrors "teacherDTO.lastName","error" />
    </div>
    <div class="form-group">
        <label class="form-control-label">Email</label>
        <@spring.formInput "teacherDTO.email" />
        <@spring.showErrors "teacherDTO.email","error" />
        <#if emailFail??>
            <span style="color:red">${emailFail}</span>
        </#if>
    </div>

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
