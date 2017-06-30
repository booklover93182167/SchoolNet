<#import "../header.ftl" as h>
<@h.header>

</@h.header>
<link rel="stylesheet" type="text/css" href="/scripts/admin-home-popup.css">
<br>
<div id="header">
    <h2>
        <span><@spring.message "hello"/> ,${currentUser.login}</span>
        <button id="create" class="btn btn-primary float-right create-teacher-management"
                onclick="window.location.href='/freemarker/admin-home/createSchool'">
            <span class="fa fa-plus"></span>
            <span>
            <@spring.message "school.createNew"/>
            </span>
        </button>
        <button id="deleted" class="btn btn-danger float-right deleted-teacher-management"
                onclick="window.location.href='/freemarker/admin-home/deletedSchool'" style="margin-right: 10px">
            <span class="fa fa-danger"></span>
            <span>
            <@spring.message "school.deletedSchools"/>
            </span>
        </button>
    </h2>
</div>
<div id="content">
    <table class="table table-striped">
        <tr>
            <th colspan="7"><@spring.message "school.list"/></th>
        </tr>
        <tr>
            <th><@spring.message "school.name"/></th>
            <th><@spring.message "school.status"/></th>
        </tr>
    <#list schools as school>
        <tr>

            <td>${school.name}</a></td>
            <td>
                <button class="badge badge-success hand"
                        onclick="showDisableModal(${school.id})">
                    <@spring.message "enabled"/>
                </button>
            </td>
            <td>
                <div class="btn-group flex-btn-group-container">
                    <button id="view" type="button" class="btn btn-info btn-sm"
                            onclick="window.location.href='/freemarker/admin-home/details/${school.id}'">
                        <span class="fa fa-eye"></span>
                        <span class="hidden-md-down"><@spring.message "school.view"/></span>
                    </button>
                </div>
            </td>


            <div class="deleteModal" id="delete${school.id}">
                <div class="modal-content2">
                    <div class="modal-body">
                        <h2><@spring.message "school.disableconf"/> ${school.name} <@spring.message "school.disable?"/></h2>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"
                                onclick="removeDisableModal(${school.id})">
                            <span class="fa fa-ban"></span>&nbsp;<span><@spring.message "school.cancel"/></span>
                        </button>
                        <button type="submit" class="btn btn-success"
                                onclick="window.location.href='/freemarker/admin-home/school-toggle/${school.id}'">
                            <span class="fa fa-check"></span><span><@spring.message "yes"/></span>
                        </button>
                    </div>
                </div>
            </div>

        </tr>
    </#list>

    <#if longs gt 0 >
        <div>
            <form id="use">
                <select name="size" class="custom-select" id="mySelect" onchange="onChange()">
                    <#list [5, 10, 15, 20] as s>
                        <#if sizes == s>
                            <option value="${s}" selected="selected">${s} items</option>
                        <#else>
                            <option value="${s}">${s} items</option>
                        </#if>
                    </#list>
                </select>
            </form>
            <nav aria-label="...">
                <ul class="pagination">
                    <#if current gt 0 >
                        <li class="page-item">
                            <a class="page-link" href="?page=${current-1}&size=${sizes}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                    </#if>
                    <#list 0..longs-1 as i>
                        <#if current != i>
                            <li class="page-item"><a class="page-link" href="?page=${i}&size=${sizes}">${i+1}</a></li>
                        <#else>
                            <li class="page-item active"><span class="page-link">${i+1}</span></li>
                        </#if>
                    </#list>
                    <#if current < longs-1  >
                        <li class="page-item">
                            <a class="page-link" href="?page=${current+1}&size=${sizes}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </#if>
                </ul>
            </nav>
        </div>
    </#if>
    </table>
    <span></span>
</div>
<script src="/scripts/admin-home.js"></script>

<@h.footer>

</@h.footer>
