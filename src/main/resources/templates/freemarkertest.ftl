<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>FreeMarker Spring MVC Hello World</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#locales").change(function () {
                var selectedOption = $('#locales').val();
                if (selectedOption != ''){
                    window.location.replace(window.location.pathname + '?lang=' + selectedOption);
                }
            });
        });
    </script>
</head>
<body>

<div style="float: right">
    <select id="locales">
        <option value=""><@spring.message "lang.select"/></option>
        <option value="en"><@spring.message "lang.en"/></option>
        <option value="ru"><@spring.message "lang.ru"/></option>
        <option value="ua"><@spring.message "lang.ua"/></option>
    </select>
</div>

<div id="header">
    <h2><@spring.message "greeting"/></h2>
</div>
<div id="content">
<#--<script type="text/javascript" src="/temlates/test.js"></script>-->
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
    <a href="logout"><@spring.message "logout"/></a>
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
</body>
</html>
