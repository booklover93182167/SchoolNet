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
        <form name="loginVM" action="/freemarker/authenticate" method="post">
            Login: <input type="text" name="username"/> <br/>
            Password: <input type="password" name="password"/> <br/>
            Remember Me: <select name="rememberMe">
            <option value="true">True</option>
            <option value="false">False</option>
        </select>
            <input type="submit" value="Submit"/>
        </form>
    </fieldset>
</div>
<@h.footer>

</@h.footer>
