<#import "../header.ftl" as h>
<@h.header>

</@h.header>
<link rel="stylesheet" type="text/css" href="/scripts/teacher-mgmt-popup.css">

<br>
<div id="header">
    <h2>
        <span><@spring.message "teacherm.list"/></span>
    </h2>
</div>

<form name="create" action="/freemarker/teacher-mgmt/teacher-mgmt-lessons" method="post">
    <div class="body">
        <div class="form-group">
            <label class="form-control-label" for="name">Lesson name</label>
            <input type="text" class="form-control" id="lessonName" name="lessonName" minlength=3 maxlength=50 required>
        </div>
        Enabled
        <select name="enabled">
            <option value="true"><@spring.message "true"/></option>
            <option value="false"><@spring.message "false"/></option>
        </select>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-info"
                onclick="window.location.href='/freemarker/teacher-mgmt/'">
            <span class="fa fa-arrow-left"></span>&nbsp;<span><@spring.message "back"/></span>
        </button>
        <button type="button" class="btn btn-default" data-dismiss="modal"
                onclick="reset()">
            <span class="fa fa-ban"></span>&nbsp;<span><@spring.message "school.cancel"/></span>
        </button>
        <button type="submit" class="btn btn-primary">
            <span class="fa fa-save"></span>&nbsp;<span><@spring.message "school.save"/></span>
        </button>
    </div>
</form>
<div class="modal-body">
    <table class="table table-striped">
        <tr>
            <th colspan="7">Lessons list</th>
        </tr>
        <tr>
            <th>Lesson ID</th>
            <th>Lesson name</th>
        </tr>
    <#list model["lessonList"] as lesson>
        <tr>
            <td>${lesson.id}</td>
            <td>${lesson.name}</a></td>
        </tr>
    </#list>
    </table>
    <span></span>

</div>
<script src="/scripts/teachermgmt.js"></script>

<@h.footer>

</@h.footer>
