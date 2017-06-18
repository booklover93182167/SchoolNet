<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title><@locale.message "parent.page.title"/></title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/scripts/languageChanger.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
        /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

        /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {height: 450px}

        /* Set gray background color and 100% height */
        .sidenav {
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
        }

        /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

        /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }
            .row.content {height:auto;}
        }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/freemarker/parent">SchoolNet</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/freemarker/freemarkertest">FreeMarkerTest</a></li>
                <li><a href="/freemarker/teachers/1">Teacher</a></li>
                <li class="active"><a href="/freemarker/parent">Parent</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> <@locale.message "my.account"/></a></li>
                <li><a href="/freemarker/login"><span class="glyphicon glyphicon-log-in"></span> <@locale.message "login"/></a></li>
                <li><a href="/freemarker/logout"><span class="glyphicon glyphicon-log-out"></span> <@locale.message "logout"/></a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">

        </div>
        <div class="col-sm-8 text-left">
            <h1><@locale.message "parent.page.title"/></h1>
            <p>
                <@locale.message "pupil.list"/>
                <ul class="nav nav-pills" id="myTab" role="tablist">
                    <#list model["pupilList"] as pupil>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#pupil${pupil.id}" role="tab" aria-controls="pupil${pupil.id}">${pupil.firstName} ${pupil.lastName}, ${pupil.formName}</a>
                        </li>
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
        <div class="col-sm-2 sidenav">

            <select class="form-control" id="locales">
                <option value=""><@locale.message "lang.select"/></option>
                <option value="en"><@locale.message "lang.en"/></option>
                <option value="ru"><@locale.message "lang.ru"/></option>
                <option value="ua"><@locale.message "lang.ua"/></option>
            </select>

            <#--<div class="well">-->
                <#--<p>ADS</p>-->
            <#--</div>-->
            <#--<div class="well">-->
                <#--<p>ADS</p>-->
            <#--</div>-->
        </div>
    </div>
</div>

<footer class="container-fluid text-center">
    <p><@locale.message "footer.text"/></p>
</footer>

</body>
</html>
