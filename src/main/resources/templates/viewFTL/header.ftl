<#macro header>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
          integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!--
    <link rel="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <link rel="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    -->
    <title>SOME</title>
</head>
<body>
<div>
<nav class="navbar navbar-inverse navbar-toggleable-md">
    <div class="navbar-collapse collapse">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown pointer">
                <a class="nav-link dropdown-toggle"id="teacher-menu">
                    <span>
                        <i class="fa fa-th-list" aria-hidden="true"></i>
                        <span>Teacher Menu</span>
                        <b class="caret"></b>
                    </span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a class="dropdown-item">
                            <i class="fa fa-home" aria-hidden="true"></i>
                            <span>Teacher home</span>
                        </a>
                    </li>
                    <li>
                        <a class="dropdown-item">
                            <i class="fa fa-calendar" aria-hidden="true"></i>
                            <span>Teacher Schedule</span>
                        </a>
                    </li>
                </ul>
            </li>


            <li class="nav-item dropdown pointer">
                <a class="nav-link dropdown-toggle" href="javascript:void(0);" >
                    <span>
                        <i class="fa fa-th-list" aria-hidden="true"></i>
                        <span>Forms</span>
                        <b class="caret"></b>
                    </span>
                </a>
                <ul class="dropdown-menu">
                    <li uiSrefActive="active">
                        <a class="dropdown-item" (click)="collapseNavbar()">
                            <i class="fa fa-home" aria-hidden="true"></i>
                            <span>Forms</span>
                        </a>
                    </li>
                    <li uiSrefActive="active" >
                        <a class="dropdown-item" (click)="collapseNavbar()">
                            <i class="fa fa-calendar" aria-hidden="true"></i>
                            <span>My form</span>
                        </a>
                    </li>
                </ul>
            </li>

            <li class="nav-item dropdown pointer">
                <a class="nav-link dropdown-toggle" href="javascript:void(0);" id="pupil-menu">
                    <span>
                        <i class="fa fa-home" aria-hidden="true"></i>
                        <span>
                            Pupil Home
                        </span>
                        <b class="caret"></b>
                    </span>
                </a>
                <ul class="dropdown-menu">
                    <li uiSrefActive="active">
                        <a class="dropdown-item" (click)="collapseNavbar()">
                            <i class="fa fa-calendar" aria-hidden="true"></i>
                            <span>Pupil Home</span>
                        </a>
                    </li>
                    <li uiSrefActive="active">
                        <a class="dropdown-item" (click)="collapseNavbar()">
                            <i class="fa fa-tasks" aria-hidden="true"></i>
                            <span>Pupil Grades</span>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link" (click)="collapseNavbar()">
                    <i class="fa fa-home" aria-hidden="true"></i>
                    <span>Home</span>
                </a>
            </li>
            <!-- jhipster-needle-add-element-to-menu - JHipster will add new menu items here  *ngSwitchCase="true"-->

            <li  class="nav-item dropdown pointer">
                <a class="nav-link dropdown-toggle" href="javascript:void(0);" id="head-teacher-menu">
                    <span>
                        <i class="fa fa-tasks" aria-hidden="true"></i>
                        <span>
                            Management
                        </span>
                        <b class="caret"></b>
                    </span>
                </a>
                <ul class="dropdown-menu">
                    <li uiSrefActive="active">
                        <a class="dropdown-item" (click)="collapseNavbar()">
                            <i class="fa fa-user" aria-hidden="true"></i>
                            <span>Teachers</span>
                        </a>
                    </li>

                    <li uiSrefActive="active">
                        <a class="dropdown-item" (click)="collapseNavbar()">
                            <i class="fa fa-building-o" aria-hidden="true"></i>
                            <span>Forms</span>
                        </a>
                    </li>

                    <li uiSrefActive="active">
                        <a class="dropdown-item" (click)="collapseNavbar()">
                            <i class="fa fa-calendar" aria-hidden="true"></i>
                            <span>Classrooms</span>
                        </a>
                    </li>

                    <li uiSrefActive="active">
                        <a class="dropdown-item" (click)="collapseNavbar()">
                            <i class="fa fa-calendar" aria-hidden="true"></i>
                            <span>Schedule</span>
                        </a>
                    </li>
                </ul>
            </li>
            <li class="nav-item dropdown pointer">
                <a class="nav-link dropdown-toggle" href="javascript:void(0);" id="languagesnavBarDropdown">
                    <span>
                        <i class="fa fa-flag" aria-hidden="true"></i>
                        <span>Language</span>
                        <b class="caret"></b>
                    </span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a class="dropdown-item" href="javascript:void(0);" (click)="changeLanguage(language);collapseNavbar();">Lang</a>
                    </li>
                </ul>
            </li>
            <li class="nav-item dropdown pointer">
                <a class="nav-link dropdown-toggle" href="javascript:void(0);" id="account-menu">
                  <span>
                    <i class="fa fa-user" aria-hidden="true"></i>
                    <span>
                      Account
                    </span>
                    <b class="caret"></b>
                  </span>
                    <span>
                      image
                  </span>
                    <span>
                        login
                    </span>
                </a>
                <ul class="dropdown-menu dropdown-menu-right">
                    <li>
                        <a class="dropdown-item" (click)="collapseNavbar()">
                            <i class="fa fa-fw fa-wrench" aria-hidden="true"></i>
                            <span>Settings</span>
                        </a>
                    </li>
                    <li>
                        <a class="dropdown-item" (click)="collapseNavbar()">
                            <i class="fa fa-fw fa-clock-o" aria-hidden="true"></i>
                            <span>Password</span>
                        </a>
                    </li>
                    <li>
                        <a class="dropdown-item" (click)="logout()" id="logout">
                            <i class="fa fa-fw fa-sign-out" aria-hidden="true"></i>
                            <span>Sign out</span>
                        </a>
                    </li>
                    <li>
                        <a class="dropdown-item" (click)="login()" id="login">
                            <i class="fa fa-fw fa-sign-in" aria-hidden="true"></i>
                            <span>Sign in</span>
                        </a>
                    </li>
                    <li>
                        <a class="dropdown-item" (click)="collapseNavbar()">
                            <i class="fa fa-fw fa-registered" aria-hidden="true"></i>
                            <span>Register</span>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
</div>
</#macro>
<!-- http://www.vogella.com/tutorials/FreeMarker/article.html#freemarkertricks -->

<#macro footer>
<div>FOOTER</div>
</body>
</html>
</#macro>
