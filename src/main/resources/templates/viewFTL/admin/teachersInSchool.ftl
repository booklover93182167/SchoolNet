<#import "../header.ftl" as h>
<@h.header>

</@h.header>


<div id="header">
    <h2></h2>
</div>
<div id="content">

    <br/>
    <table class="table table-striped">
        <h2>
            Teachers list by  school ${schoolId}
        </h2>
        <tr>
            <th id="demo">First Name</th>  <th>Last Name</th> <th>E-mail</th>
        </tr>
    <#list model["teachersList"] as teacher>
        <tr>
            <td>${teacher.firstName}  </td> <td>${teacher.lastName}  </td> <td>${teacher.email}</td>
        </tr>
    </#list>
    </table>
    <br/>


    <script src="/scripts/test.js"></script>


    <input type="button" value="external function" id="externalJs" onclick="myFunction()">


</div>




<@h.footer>

</@h.footer>
