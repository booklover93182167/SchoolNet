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
            <span style="color:red"><@spring.showErrors "loginVM.username","error" /></span>
            <br>
            Password:
            <@spring.formPasswordInput "loginVM.password" />
            <span style="color:red"><@spring.showErrors "loginVM.password","error" /></span>
            <br>
            <#if loginFail??>
                <span style="color:red">${loginFail}<br></span>
            </#if>
            <input type="submit" value="Submit"/>
        </form>
    </fieldset>
</div>
<@h.footer>

</@h.footer>
