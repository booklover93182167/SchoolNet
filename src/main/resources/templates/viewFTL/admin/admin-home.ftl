<#import "../header.ftl" as h>
<@h.header>

</@h.header>

<br>
<div id="header">
    <h2>
        <span><@spring.message "hello"/> ,</span>
        <button id="create" class="btn btn-primary float-right create-teacher-management"
                onclick="window.location.href='/freemarker/admin-home/createSchool'">
            <span class="fa fa-plus"></span>
            <span>
            <@spring.message "school.createNew"/>
            </span>
        </button>
    </h2>
</div>
<div id="content">
    <table class="table table-striped">
        <tr>
            <th colspan="7"><@spring.message "school.list"/></th>
        </tr>
        <tr>
            <th><@spring.message "school.name"/></th>
            <th><@spring.message "school.id"/></th>
        </tr>
    <#list model["schoolList"] as school>
        <tr>
            <td>${school.name}</a></td>
            <td>${school.id}</td>
            <td>
                <div class="btn-group flex-btn-group-container">
                    <button id="view" type="submit" class="btn btn-info btn-sm"
                            onclick="window.location.href='/freemarker/admin-home/details/${school.id}'">
                        <span class="fa fa-eye"></span>
                        <span class="hidden-md-down"><@spring.message "school.view"/></span>
                    </button>
                    <button id="edit" type="submit" class="btn btn-primary btn-sm">
                        <span class="fa fa-pencil"></span>
                        <span class="hidden-md-down"><@spring.message "school.edit"/></span>
                    </button>
                    <button id="delete" type="submit" class="btn btn-danger btn-sm">
                        <span class="fa fa-remove"></span>
                        <span class="hidden-md-down"><@spring.message "school.delete"/></span>
                    </button>
                </div>
            </td>
        </tr>
    </#list>
    </table>
    <span></span>
</div>


<script src="/scripts/newSchool.js"></script>

<@h.footer>

</@h.footer>

