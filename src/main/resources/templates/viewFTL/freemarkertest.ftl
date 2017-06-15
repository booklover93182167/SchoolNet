<#import "header.ftl" as h>
<@h.header>

</@h.header>

    <div id="content">
    <fieldset>
        <legend>Add School</legend>
        <form name="schoolDTO" action="/freemarker/freemarkertest/add" method="post">
            Name: <input type="text" name="name"/> <br/>
            Enabled: <select name="enabled">
            <option value="true">True</option>
            <option value="false">False</option>
        </select>
            <input type="submit" value="Save"/>
        </form>
    </fieldset>
    <br/>
    <table>
        <tr>
            <th colspan="2">Schools List</th>
        </tr>
        <tr>
            <th>Name</th>
            <th>ID</th>
        </tr>
    <#list model["schoolList"] as school>
        <tr>
            <td><a href="/freemarker/teachers/${school.id}">${school.name}</a></td>
            <td>${school.id}</td>
        </tr>
    </#list>
    </table>
</div>
<script src=""></script>
<@h.footer>

</@h.footer>
