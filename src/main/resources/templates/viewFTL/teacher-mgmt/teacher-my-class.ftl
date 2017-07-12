<#import "../header.ftl" as h>
<@h.header>

</@h.header>
<link rel="stylesheet" type="text/css" href="/scripts/teacher-mgmt-popup.css">
<br>
<div id="header">
    <h2>
        <span><@spring.message "hello"/>, ${model.currentUser.firstName} ${model.currentUser.lastName}</span>
        <br>
        <span><@spring.message "teacher.class"/> ${model.formName} </span>
    </h2>
</div>
<table class="table table-hover" >
    <thead>
    <tr>
        <th style="width: 35%">Учень</th>
        <th style="width: 65%">Батьки</th>

    </tr>
    </thead>
    <tfoot>
    <tr>
        <th colspan="2"><button type="button" class="btn btn-outline-primary">New pupil</button></th>

    </tr>
    </tfoot>
    <tbody>
    <tr>
        <td>Cell</td>
        <td>Cell
            <button type="button" class="btn btn-outline-info btn-sm" style="margin-left: 70px">New parent</button></td>

    </tr>


    </tbody>
</table>
<@h.footer>

</@h.footer>
