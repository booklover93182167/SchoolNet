<#import "../header.ftl" as h>
<@h.header>

</@h.header>


<div id="header">
    <h2></h2>
</div>
<div id="content">
    <button id="create" class="btn btn-primary float-right create-teacher-management"
            onclick="window.location.href='/freemarker/admin-home/newSchool/'">
        <span class="fa fa-plus"></span>
        <span>
            Create a new school
            </span>
    </button>
    <fieldset>
        <legend><@spring.message "school.add"/></legend>
        <form name="schoolDTO" action="/freemarker/admin-home/add" method="post">
        <@spring.message "school.name"/>: <input type="text" name="name"/>
            <br/><@spring.message "school.enabled"/>:
            <select name="enabled">
                <option value="true"><@spring.message "true"/></option>
                <option value="false"><@spring.message "false"/></option>
            </select>
            <br/><input type="submit" value="<@spring.message "save"/>"/>
        </form>
    </fieldset>
    <br/>
    <table class="table table-striped">
        <tr>
            <th><@spring.message "school.name"/></th>
            <th><@spring.message "school.headTeacher"/></th>
        </tr>
    <#list model["schoolList"] as school>
        <tr>
            <td><a href="/freemarker/teachersInSchool/${school.id}">${school.name}</a></td>
            <td>${school.id}</td>
        </tr>
    </#list>
    </table>
</div>


<script src="/scripts/newSchool.js"></script>

<@h.footer>

</@h.footer>

