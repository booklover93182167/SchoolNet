<#import "../header.ftl" as h>
<@h.header>

</@h.header>

<br>
<div id="header">
    <h2>
        <span>Hello, ${model.currentTeacher.firstName} ${model.currentTeacher.lastName}</span>
        <button id="create" class="btn btn-primary float-right create-teacher-management">
            <span class="fa fa-plus"></span>
            <span>
            Create a new Teacher
            </span>
        </button>
    </h2>
</div>
<div id="content">
    <table class="table table-striped">
        <tr>
            <th colspan="7">Teachers in my School</th>
        </tr>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Enabled</th>
            <th>Form</th>
            <th>Lessons</th>
            <th>E-Mail</th>
        </tr>
    <#list model["teachers"] as teacher>
        <tr>
            <td>${teacher.firstName}</td>
            <td>${teacher.lastName}</td>
            <td>${teacher.enabled?c}</td>
            <td>${teacher.formId!""}</td>
            <td>
                <#list teacher.lessons as lesson>
                    ${lesson.name}<#if lesson_has_next>,</#if>
                </#list>
            </td>
            <td>${teacher.email!""}</td>
            <td>
                <div class="btn-group flex-btn-group-container">
                    <button id="view" type="submit" class="btn btn-info btn-sm"
                            onclick="window.location.href='/freemarker/teacher-mgmt/teacher-mgmt-details/${teacher.id}'">
                        <span class="fa fa-eye"></span>
                        <span class="hidden-md-down">View</span>
                    </button>
                    <button id="edit" type="submit" class="btn btn-primary btn-sm">
                        <span class="fa fa-pencil"></span>
                        <span class="hidden-md-down">Edit</span>
                    </button>
                    <button id="delete" type="submit" class="btn btn-danger btn-sm">
                        <span class="fa fa-remove"></span>
                        <span class="hidden-md-down">Delete</span>
                    </button>
                </div>
            </td>
        </tr>
    </#list>
    </table>
    <span></span>
</div>

<script src="/scripts/teachermgmt.js"></script>


<@h.footer>

</@h.footer>
