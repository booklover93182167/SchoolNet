<#import "header.ftl" as h>
<@h.header>

</@h.header>


<div id="header">
    <h2></h2>
</div>
<div id="content">
    <fieldset>
        <legend><@locale.message "school.add"/></legend>
        <form name="schoolDTO" action="/freemarker/freemarkertest/add" method="post">
        <@locale.message "school.name"/>: <input type="text" name="name"/>
            <br/><@locale.message "school.enabled"/>:
            <select name="enabled">
                <option value="true"><@locale.message "true"/></option>
                <option value="false"><@locale.message "false"/></option>
            </select>
            <br/><input type="submit" value="<@locale.message "save"/>"/>
        </form>
    </fieldset>
    <br/>
    <a href="logout"><@locale.message "logout"/></a>
    <table>
        <tr>
            <th colspan="2"><@locale.message "school.list"/></th>
        </tr>
        <tr>
            <th><@locale.message "school.name"/></th>
            <th><@locale.message "school.id"/></th>
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

