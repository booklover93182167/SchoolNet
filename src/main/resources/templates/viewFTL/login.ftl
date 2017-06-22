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
            <table>
                <tr>
                    <td>
                        Login:
                    </td>
                    <td>
                    <@spring.formInput "loginVM.username" />
                    </td>
                    <td>
                    <span style="color:red"><@spring.showErrors "loginVM.username","error" /></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        Password:
                    </td>
                    <td>
                    <@spring.formPasswordInput "loginVM.password" />
                    </td>
                    <td>
                    <span style="color:red"><@spring.showErrors "loginVM.password","error" /></span>
                    </td>
                    <td>
                    <#if loginFail??>
                        <span style="color:red">${loginFail}<br></span>
                    </#if>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Submit"/>
        </form>
    </fieldset>
</div>
<@h.footer>

</@h.footer>
