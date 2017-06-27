<#import "../header.ftl" as h>
<@h.header>

</@h.header>
<link rel="stylesheet" type="text/css" href="/scripts/teacher-mgmt-popup.css">

<br>
<div id="header">
    <h2>
        <span>List of school lessons</span>
    </h2>
</div>

<div class="errorModal" id="errorPopup">
    <div class="modal-content3">
        <div class="modal-body">
            <h3>
                <span style="color:red">
                    <script>
                        <#if emailFail??>
                            document.getElementById('errorPopup').style.display = "block";
                        </#if>
                    </script>
                <@spring.message "teacherm.emailFail"/>
                </span>
            </h3>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-info"
                    onclick=hideErrorModal()>
                <span class="fa fa-arrow-left"></span>&nbsp;<span><@spring.message "back"/></span>
            </button>
        </div>
    </div>
</div>
<script src="/scripts/teachermgmt.js"></script>

<@h.footer>

</@h.footer>
