<#import "header.ftl" as h>

<#assign pagetitle><@spring.message "login.page.title"/></#assign>

<@h.header
    pagetitle = "${pagetitle}"
    cssSources = [
    "/scripts/login.css"
    ]>
</@h.header>


<div class="container">

    <form class="form-signin" name="login" action="/freemarker/authenticate" method="post">
        <h2 class="form-signin-heading"><@spring.message "login.page.title"/></h2>
        <label for="inputEmail" class="sr-only"><@spring.message "login.login"/></label>
        <@spring.formInput "loginVM.username" />
        <@spring.showErrors "loginVM.username","error" />
        <label for="inputPassword" class="sr-only"><@spring.message "login.password"/></label>
        <@spring.formPasswordInput "loginVM.password" />
        <@spring.showErrors "loginVM.username","error" />
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> <@spring.message "login.rememberme"/>
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><@spring.message "login.signin"/></button>
    </form>

    <script>
        $("#username").addClass("form-control");
        $("#username").attr("placeholder", "<@spring.message 'login.login'/>");
        $("#username").prop('required', true);
        $("#password").addClass("form-control");
        $("#password").attr("placeholder", "<@spring.message 'login.password'/>");
        $("#password").prop('required', true);
    </script>

</div>

<@h.footer>

</@h.footer>
