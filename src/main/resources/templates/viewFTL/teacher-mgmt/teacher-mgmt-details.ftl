<#import "../header.ftl" as h>
<@h.header>

</@h.header>

<h2>${teacher.firstName} ${teacher.lastName}</h2>
<dl class="row-md jh-entity-details">
    <dt><span>Form</span></dt>
    <dd>
        <div>
            <span>${teacher.formName}</span>
        </div>
    </dd>

    <dt><span>Email</span></dt>
    <dd>
        <div>
            <span>${teacher.email!""}</span>
        </div>
    </dd>

    <dt><span>Login</span></dt>
    <dd>
        <div>
            <span>${teacher.login}</span>
        </div>
    </dd>

    <dt><span>Lessons</span></dt>
    <dd>
            <span>
            <#list teacher.lessons as lesson>
            ${lesson.name}<#if lesson_has_next>,</#if>
            </#list>
            </span>
    </dd>
</dl>

<button type="submit" class="btn btn-info"
        onclick="window.location.href='/freemarker/teacher-mgmt/teacher-mgmt/'">
    <span class="fa fa-arrow-left"></span>&nbsp;<span>Back</span>
</button>

<@h.footer>

</@h.footer>
