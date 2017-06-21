<#import "header.ftl" as h>
<@h.header>

</@h.header>

<div id="content">
    <fieldset>
        <legend><@spring.message "school.add"/></legend>
        <form name="schoolDTO" action="/freemarker/freemarkertest/add" method="post">
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
    <a href="logout"><@spring.message "login.signout"/></a>
    <table>
        <tr>
            <th colspan="2"><@spring.message "school.list"/></th>
        </tr>
        <tr>
            <th><@spring.message "school.name"/></th>
            <th><@spring.message "school.id"/></th>
        </tr>
        <#list model["schoolList"] as school>
            <tr>
                <td><a href="/freemarker/teachers/${school.id}">${school.name}</a></td>
                <td>${school.id}</td>
            </tr>
        </#list>
    </table>
</div>
<@h.footer>

</@h.footer>
