<!DOCTYPE html>
<html>
<head>
    <title>Teachers list</title>
<body>


<div id="content">

    <br/>
    <table width="200" border="1"
           cellpadding="4" cellspacing="0">
        <tr>
            <th colspan="3">Teachers list by school id # ${schoolId}</th>
        </tr>
        <tr>
            <th>First Name</th>  <th>Last Name</th> <th>E-mail</th>
        </tr>
        <#list model["teachersList"] as teacher>
            <tr>
                <td>${teacher.firstName}  </td> <td>${teacher.lastName}  </td> <td>${teacher.email}</td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>
