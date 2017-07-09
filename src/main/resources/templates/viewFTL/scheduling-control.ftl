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
                <tbody">
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
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="col-form-label" for="date">Date from table</label>
                            <fieldset disabled>
                                <input class="form-control" type="date" value="" id="date">
                            </fieldset>
                        </div>
                        <div class="col-md-6">
                            <label class="col-form-label" for="lesson_position">Lesson position from table</label>
                            <fieldset disabled>
                                <select id="lesson_position" class="form-control"></select>
                            </fieldset>
                        </div>
                        <div class="col-md-12">
                            <label class="col-form-label" for="teacher">Teacher name</label>
                            <fieldset disabled>
                                <input type="text" id="teacher" class="form-control">
                            </fieldset>
                        </div>

                        <div class="col-md-6">
                            <label class="col-form-label" for="lesson">Lesson</label>
                            <select class="form-control" id="lesson"></select>
                        </div>
                        <div class="col-md-3">
                            <label class="col-form-label" for="lesson_type">Lesson type</label>
                            <select class="form-control" id="lesson_type"></select>
                        </div>
                        <div class="col-md-3">
                            <label class="col-form-label" for="form_name">Form</label>
                            <select class="form-control" id="form_name"></select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <span class="fa fa-ban"></span>&nbsp;<span><@spring.message "school.cancel"/></span>
                        </button>
                        <button type="submit" class="btn btn-success" data-dismiss="modal">
                        <#--onclick="saveSchedule()">-->
                            <span class="fa fa-save"></span>&nbsp;<span><@spring.message "save"/></span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/scripts/scheduling-control.js"></script>

<@h.footer></@h.footer>
