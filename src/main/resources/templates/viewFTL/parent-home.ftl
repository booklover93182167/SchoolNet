<#import "header.ftl" as h>

<@h.header>

</@h.header>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav"></div>
        <div class="col-sm-8 text-left">
            <h1><@spring.message "parent.page.title"/></h1>
            <p>
                <@spring.message "pupil.list"/>
                <ul class="nav nav-pills" id="myTab" role="tablist">
                    <#list model["pupilList"] as pupil>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#pupil${pupil.id}" role="tab" aria-controls="pupil${pupil.id}">${pupil.firstName} ${pupil.lastName} [${pupil.formName}]</a>
                        </li>
                    <#else>
                        You haven't children
                    </#list>
                </ul>
                <div class="tab-content">
                    <#list model["pupilList"] as pupil>
                        <div class="tab-pane" id="pupil${pupil.id}" role="tabpanel">Info about pupil ${pupil.id}</div>
                    </#list>
                </div>
                <script>
                    $(function () {
                        $('#myTab a:first').tab('show')
                    })
                </script>
            </p>
        </div>
        <div class="col-sm-2 sidenav"></div>
    </div>
</div>

<@h.footer>

</@h.footer>
