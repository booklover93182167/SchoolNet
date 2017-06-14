<!DOCTYPE html>
<html>
<head>
    <title>Teachers list</title>
    <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
    <#--<script type="text/javascript" src="./resources/test.js"></script>-->
    <#--<script type="text/javascript" src="<@s.url includeParams='{schoolId}' value='/templates/test.js' />></script>-->

    <#--<script src="{themeUrl('/test.js')}"></script>-->
    <#--<script type="text/javascript" src="${javascript_folder}/test.js"></script>-->
    <#--<@include_page path="test.js"/>-->

    <script>
        function myFunction() {
            document.getElementById("demo").innerHTML = "Paragraph changed.";
        }
    </script>
</head>
<body>


<div id="content">

    <br/>
    <table width="200" border="1"
           cellpadding="4" cellspacing="0">
        <tr>
            <th colspan="3">Teachers list by school id # ${schoolId}</th>
        </tr>
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

    <input type="button" value="js test" id="elem_Js">

    <script>
        elem_Js.onclick = function(event) {
            alert('JavaScript function from ftl file');
        }
    </script>

    <input type="button" value="JQuery test" id="elem_jquery">
    <input type="button" value="js in header test" id="externalJs" onclick="myFunction()">

    <#--<script>-->
        <#--$(elem_jquery).on('click', function() {-->
            <#--$('div').hide();-->
            <#--alert('JQuery function from ftl file');-->
        <#--})-->
    <#--</script>-->

    <#--<script>-->
        <#--external1();-->
    <#--</script>-->
</div>
</body>
</html>
