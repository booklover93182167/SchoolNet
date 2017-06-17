<#import "header.ftl" as h>
<@h.header>

</@h.header>
<div>
    <h2>
        Login to SchoolNet
    </h2>
</div>

<div id="content">
    <fieldset>
        <legend>Login</legend>
        <form name="login" action="/freemarker/authenticate" method="post">
            Login:
            <@spring.formInput "loginVM.username" />
            <@spring.showErrors "loginVM.username","error" />
            <br>
            Password:
            <@spring.formInput "loginVM.password" />
            <@spring.showErrors "loginVM.username","error" />
            <br>
            <input type="submit" value="Submit"/>
        </form>
    </fieldset>
</div>
<@h.footer>

</@h.footer>
