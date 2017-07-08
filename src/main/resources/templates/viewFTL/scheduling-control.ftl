<#import "header.ftl" as h>
<@h.header
cssSources = [
"/scripts/bootstrap-datepiker/datepicker.css",
"/scripts/scheduling-control.css"]
jsSources = [
"/scripts/bootstrap-datepiker/bootstrap-datepicker.js",
"/scripts/bootstrap-datepiker/bootstrap-datepicker.uk.js",
"/scripts/bootstrap-datepiker/bootstrap-datepicker.ru.js"
]/>

<div class="container-fluid">
    <div class="row">
        <div class="page-header">
            <h3>Scheduling control</h3>
        </div>
    </div>

    <div class="row">

        <div class="col-md-3">
            <br>
            <div class="row">
                <div class="datepicker" style="margin: auto"></div>
            </div>
            <br>
            <div class="row">
                <select class="form-control" id="target">
                    <option value="BY_TEACHER">Teacher</option>
                    <option value="BY_FORM">Form</option>
                    <option value="BY_CLASSROOM">Classroom</option>
                </select>
            </div>
            <br>
            <div class="row" id="target-element">
                <select title="select" class="form-control"></select>
            </div>
        </div>
        <div class="col-md-9" id="schedule">
            <table class="table table-bordered table-hover" style="margin: auto">
                <thead>
                <th># Pos</th>
                <#list 1..7 as week_day>
                    <th id="${week_day}"></th>
                </#list>
                </thead>
                <tbody>
                <#list 1..10 as row>
                <tr id="${row}">
                    <td>${row}</td>
                    <#list 0..6 as col>
                        <td isEmpty="true" data-toggle="modal" data-target="#modal">-</td>
                    </#list>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="container">
    <div class="modal fade .modal-sm" id="modal">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h5 class="modal-title">Add or edit schedule</h5>
                </div>
                <div class="modal-body" ></div>
                <div class="modal-footer">
                    <a href="" class="btn btn-default" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/scripts/scheduling-control.js"></script>

<@h.footer></@h.footer>
