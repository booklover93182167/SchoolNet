<#import "../header.ftl" as h>
<@h.header>

</@h.header>
<link rel="stylesheet" type="text/css" href="/scripts/teacher-mgmt-popup.css">
<br>
<div id="header">
    <h2>
        <span>Hello, ${model.currentTeacher.firstName} ${model.currentTeacher.lastName}</span>
        <button id="create" class="btn btn-primary float-right create-teacher-management"
                onclick="window.location.href='/freemarker/teacher-mgmt/teacher-mgmt-create/'">
            <span class="fa fa-plus"></span>
            <span>
            Create a new Teacher
            </span>
        </button>
    </h2>
</div>
<div id="content">
    <table class="table table-striped">
        <thead>
        <tr>
            <th colspan="7">Teachers in my School</th>
        </tr>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Status</th>
            <th>Form</th>
            <th>Lessons</th>
            <th>E-Mail</th>
        </tr>
        </thead>
        <tbody id="teachersTable">
        <#list model["teachers"] as teacher>
        <tr>
            <td>${teacher.firstName}</td>
            <td>${teacher.lastName}</td>
            <td>
                <#if teacher.enabled!=true>
                    <button class="badge badge-danger hand"
                            onclick="window.location.href='/freemarker/teacher-mgmt-toggle/${teacher.id}'">Deactivated
                    </button></#if>
                <#if teacher.enabled=true>
                    <button class="badge badge-success hand"
                            onclick="window.location.href='/freemarker/teacher-mgmt-toggle/${teacher.id}'">Activated
                    </button></#if>
            </td>
            <td>${teacher.formName!""}</td>
            <td>
                <#list teacher.lessons as lesson>
                ${lesson.name}<#if lesson_has_next>,</#if>
                </#list>
            </td>
            <td>${teacher.email!""}</td>
            <td>
                <div class="btn-group flex-btn-group-container">
                    <button id="view" type="button" class="btn btn-info btn-sm"
                            onclick="showDetailModal(${teacher.id})">
                        <span class="fa fa-eye"></span>
                        <span class="hidden-md-down">View</span>
                    </button>
                    <button id="edit" type="submit" class="btn btn-primary btn-sm"
                            onclick="editTeacherAjax(${teacher.id})">
                        <span class="fa fa-pencil"></span>
                        <span class="hidden-md-down">Edit</span>
                    </button>
                    <button id="delete" type="submit" class="btn btn-danger btn-sm"
                            onclick="showDeleteModal(${teacher.id})">
                        <span class="fa fa-remove"></span>
                        <span class="hidden-md-down">Delete</span>
                    </button>
                </div>
            </td>
            <div id="detail${teacher.id}" class="detailModal">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2>${teacher.firstName} ${teacher.lastName}</h2>
                    </div>
                    <div class="modal-body">
                        <dl class="row-md jh-entity-details">
                            <dt><span>Form</span></dt>
                            <dd>
                                <div>
                                    <span>${teacher.formName!""}</span>
                                </div>
                            </dd>

                            <dt><span>Email</span></dt>
                            <dd>
                                <div>
                                    <span>${teacher.email!""}</span>
                                </div>
                            </dd>

                            <dt><span>Login</span></dt>
                            <dd>
                                <div>
                                    <span>${teacher.login}</span>
                                </div>
                            </dd>

                            <dt><span>Lessons</span></dt>
                            <dd>
            <span>
                <#list teacher.lessons as lesson>
                ${lesson.name}<#if lesson_has_next>,</#if>
                </#list>
            </span>
                            </dd>
                        </dl>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-info"
                                onclick=removeDetailModal(${teacher.id})>
                            <span class="fa fa-arrow-left"></span>&nbsp;<span>Back</span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="deleteModal" id="delete${teacher.id}">
                <div class="modal-content2">
                    <div class="modal-body">
                        <h2>Are you sure want to delete ${teacher.firstName} ${teacher.lastName}?</h2>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                onclick="removeDeleteModal(${teacher.id})">
                            <span class="fa fa-ban"></span>&nbsp;<span>Cancel</span>
                        </button>
                        <button type="submit" class="btn btn-danger"
                                onclick="window.location.href='/freemarker/teacher-mgmt-delete/${teacher.id}'">
                            <span class="fa fa-remove"></span>&nbsp;<span>Delete</span>
                        </button>
                    </div>
                </div>
            </div>
        </tr>
        </#list>
        </tbody>
        <div id="editPopup" class="editModal">
            <div class="modal-content3">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="form-control-label" for="firstName">First Name</label>
                        <input type="text" class="form-control" id="firstNameEdit" name="firstName" minlength=3 maxlength=50 required>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" minlength=3 maxlength=50 required>Last Name</label>
                        <input type="text" class="form-control" id="lastNameEdit" name="lastName" minlength=3 maxlength=50 required>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="email">Email</label>
                        <input type="email" class="form-control" id="emailEdit" name="email"
                               required minlength="7" maxlength="100">
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="formName">Form <span id="currentForm">-</label>
                        <button id="assign" class="btn btn-secondary"
                                         onclick="showFormAssignModal()">
                        <span class="fa fa-plus"></span>
                        <span id="assignchange">Assign</span>
                        </button>
                        <button id="remove" class="btn btn-secondary"
                                onclick="removeFormAssignment()">
                            <span class="fa fa-minus"></span>
                            <span id="assignchange">Remove</span>
                        </button>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"
                            onclick="hideEditModal()">
                        <span class="fa fa-ban"></span>&nbsp;<span>Cancel</span>
                    </button>
                    <button type="submit" class="btn btn-danger"
                            onclick="saveTeacher()">
                        <span class="fa fa-save"></span>&nbsp;<span>Save</span>
                    </button>
                </div>
            </div>
        </div>
        <div id="formAssign" class="editModal">
            <div class="modal-content3">
                <div class="modal-body">
                    <select class="form-control form-control-lg" id="forms" style="width: auto">
                        <option value="">select</option>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info"
                            onclick="hideFormAssignModal()">
                        <span class="fa fa-arrow-left"></span>&nbsp;<span>Back</span>
                    </button>
                    <button type="submit" class="btn btn-primary"
                            onclick="assignFormToTeacher()">
                        <span class="fa fa-save"></span>&nbsp;<span>Save</span>
                    </button>
                </div>
            </div>
        </div>
    </table>
</div>


<script src="/scripts/teachermgmt.js"></script>

<@h.footer>

</@h.footer>
