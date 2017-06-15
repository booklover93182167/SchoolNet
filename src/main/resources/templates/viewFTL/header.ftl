<#macro header>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
    <script src="/scripts/languageChanger.js"></script>
    <title>SOME</title>
</head>
<body>
<div style="float: right">
    <select id="locales">
        <option value=""><@locale.message "lang.select"/></option>
        <option value="en"><@locale.message "lang.en"/></option>
        <option value="ru"><@locale.message "lang.ru"/></option>
        <option value="ua"><@locale.message "lang.ua"/></option>
    </select>
</div>

<div id="header">
    <h2><@locale.message "greeting"/></h2>
</div>

<div>
    <nav class="navbar navbar-inverse navbar-toggleable-md jh-navbar">
        <div class="jh-logo-container float-left">
            <a class="jh-navbar-toggler hidden-lg-up float-right" href="javascript:void(0);" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation" (click)="toggleNavbar()">
                <i class="fa fa-bars"></i>
            </a>
            <a class="navbar-brand logo float-left" routerLink="/" (click)="collapseNavbar()">
                <span class="logo-img"></span>
                <span jhiTranslate="global.title" class="navbar-title">SchoolNet</span> <span class="navbar-version">{{version}}</span>
            </a>
        </div>
        <div class="navbar-collapse collapse" id="navbarResponsive" [ngbCollapse]="isNavbarCollapsed" [ngSwitch]="isAuthenticated()">
            <ul class="navbar-nav ml-auto">



                <li *jhiHasAnyAuthority="['ROLE_TEACHER']" ngbDropdown class="nav-item dropdown pointer">
                    <a class="nav-link dropdown-toggle" routerLinkActive="active" ngbDropdownToggle href="javascript:void(0);" id="teacher-menu">
                    <span>
                        <i class="fa fa-th-list" aria-hidden="true"></i>
                        <span jhiTranslate="global.menu.teacher-menu">Teacher Menu</span>
                        <b class="caret"></b>
                    </span>
                    </a>
                    <ul class="dropdown-menu" ngbDropdownMenu>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="teacher-home" (click)="collapseNavbar()">
                                <i class="fa fa-home" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.teacher-home.title">Teacher home</span>
                            </a>
                        </li>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="teacher-schedule" (click)="collapseNavbar()">
                                <i class="fa fa-calendar" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.teacher-schedule">Teacher Schedule</span>
                            </a>
                        </li>
                    </ul>
                </li>





                <li *jhiHasAnyAuthority="['ROLE_TEACHER', 'ROLE_HEAD_TEACHER']" ngbDropdown class="nav-item dropdown pointer">
                    <a class="nav-link dropdown-toggle" routerLinkActive="active" ngbDropdownToggle href="javascript:void(0);" >
                    <span>
                        <i class="fa fa-th-list" aria-hidden="true"></i>
                        <span jhiTranslate="global.menu.forms">Forms</span>
                        <b class="caret"></b>
                    </span>
                    </a>
                    <ul class="dropdown-menu" ngbDropdownMenu>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="form-my-suffix" (click)="collapseNavbar()">
                                <i class="fa fa-home" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.forms">Forms</span>
                            </a>
                        </li>
                        <li uiSrefActive="active" >
                            <a class="dropdown-item" routerLink="teacher-class" (click)="collapseNavbar()">
                                <i class="fa fa-calendar" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.teacher-my-class">My form</span>
                            </a>
                        </li>
                    </ul>
                </li>








                <li *jhiHasAnyAuthority="['ROLE_PUPIL']" ngbDropdown class="nav-item dropdown pointer">
                    <a class="nav-link dropdown-toggle" routerLinkActive="active" ngbDropdownToggle href="javascript:void(0);" id="pupil-menu">
                    <span>
                        <i class="fa fa-home" aria-hidden="true"></i>
                        <span jhiTranslate="global.menu.pupil-home.title">
                            Pupil Home
                        </span>
                        <b class="caret"></b>
                    </span>
                    </a>
                    <ul class="dropdown-menu" ngbDropdownMenu>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="pupil-home" (click)="collapseNavbar()">
                                <i class="fa fa-calendar" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.pupil-home.home">Pupil Home</span>
                            </a>
                        </li>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="pupil-home-grades" (click)="collapseNavbar()">
                                <i class="fa fa-tasks" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.pupil-home.grades">Pupil Grades</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li *jhiHasAnyAuthority="['ROLE_ADMIN', 'ROLE_HEAD_TEACHER', 'ROLE_TEACHER', 'ROLE_PARENT']" class="nav-item" routerLinkActive="active" [routerLinkActiveOptions]="{exact: true}">
                    <a class="nav-link" routerLink="/" (click)="collapseNavbar()">
                        <i class="fa fa-home" aria-hidden="true"></i>
                        <span jhiTranslate="global.menu.home">Home</span>
                    </a>
                </li>
                <!-- jhipster-needle-add-element-to-menu - JHipster will add new menu items here  *ngSwitchCase="true"-->

                <li *jhiHasAnyAuthority="['ROLE_ADMIN', 'ROLE_HEAD_TEACHER']" ngbDropdown class="nav-item dropdown pointer">
                    <a class="nav-link dropdown-toggle" routerLinkActive="active" ngbDropdownToggle href="javascript:void(0);" id="head-teacher-menu">
                    <span>
                        <i class="fa fa-tasks" aria-hidden="true"></i>
                        <span jhiTranslate="global.menu.management">
                            Management
                        </span>
                        <b class="caret"></b>
                    </span>
                    </a>
                    <ul class="dropdown-menu" ngbDropdownMenu>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="headteacher/management" (click)="collapseNavbar()">
                                <i class="fa fa-user" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.teacher-management.teachers">Teachers</span>
                            </a>
                        </li>

                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="headteacher-form" (click)="collapseNavbar()">
                                <i class="fa fa-building-o" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.teacher-management.forms">Forms</span>
                            </a>
                        </li>

                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="classroom-management" (click)="collapseNavbar()">
                                <i class="fa fa-calendar" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.teacher-management.classrooms">Classrooms</span>
                            </a>
                        </li>

                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="headteacher-schedule" (click)="collapseNavbar()">
                                <i class="fa fa-calendar" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.teacher-management.schedules">Schedule</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li *jhiHasAnyAuthority="['ROLE_ADMIN', 'ROLE_HEAD_TEACHER']" ngbDropdown class="nav-item dropdown pointer">
                    <a class="nav-link dropdown-toggle" routerLinkActive="active" ngbDropdownToggle href="javascript:void(0);" id="entity-menu">
                    <span>
                        <i class="fa fa-th-list" aria-hidden="true"></i>
                        <span jhiTranslate="global.menu.entities.main">
                            Entities
                        </span>
                        <b class="caret"></b>
                    </span>
                    </a>
                    <ul class="dropdown-menu" ngbDropdownMenu>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="school-my-suffix" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-asterisk" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.entities.schoolMySuffix">School My Suffix</span>
                            </a>
                        </li>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="form-my-suffix" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-asterisk" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.entities.formMySuffix">Form My Suffix</span>
                            </a>
                        </li>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="pupil-my-suffix" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-asterisk" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.entities.pupilMySuffix">Pupil My Suffix</span>
                            </a>
                        </li>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="teacher-my-suffix" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-asterisk" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.entities.teacherMySuffix">Teacher My Suffix</span>
                            </a>
                        </li>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="classroom-my-suffix" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-asterisk" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.entities.classroomMySuffix">Classroom My Suffix</span>
                            </a>
                        </li>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="lesson-my-suffix" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-asterisk" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.entities.lessonMySuffix">Lesson My Suffix</span>
                            </a>
                        </li>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="schedule-my-suffix" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-asterisk" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.entities.scheduleMySuffix">Schedule My Suffix</span>
                            </a>
                        </li>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="attendances-my-suffix" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-asterisk" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.entities.attendancesMySuffix">Attendances My Suffix</span>
                            </a>
                        </li>
                        <li uiSrefActive="active">
                            <a class="dropdown-item" routerLink="parent-my-suffix" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-asterisk" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.entities.parentMySuffix">Parent My Suffix</span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" routerLink="user-addon" routerLinkActive="active" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-asterisk" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.entities.userAddon">User Addon</span>
                            </a>
                        </li>
                        <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
                    </ul>
                </li>
                <li *jhiHasAnyAuthority="['ROLE_ADMIN', 'ROLE_HEAD_TEACHER']" ngbDropdown class="nav-item dropdown pointer">
                    <a class="nav-link dropdown-toggle" ngbDropdownToggle href="javascript:void(0);" id="admin-menu">
                    <span>
                        <i class="fa fa-user-plus" aria-hidden="true"></i>
                        <span jhiTranslate="global.menu.admin.main">Administration</span>
                        <b class="caret"></b>
                    </span>
                    </a>
                    <ul class="dropdown-menu" ngbDropdownMenu>
                        <li>
                            <a class="dropdown-item" routerLink="user-management" routerLinkActive="active" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-user" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.admin.userManagement">User management</span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" routerLink="jhi-metrics" routerLinkActive="active" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-tachometer" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.admin.metrics">Metrics</span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" routerLink="jhi-health" routerLinkActive="active" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-heart" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.admin.health">Health</span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" routerLink="jhi-configuration" routerLinkActive="active" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-list" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.admin.configuration">Configuration</span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" routerLink="audits" routerLinkActive="active" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-bell" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.admin.audits">Audits</span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item" routerLink="logs" routerLinkActive="active" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-tasks" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.admin.logs">Logs</span>
                            </a>
                        </li>
                        <li *ngIf="swaggerEnabled">
                            <a class="dropdown-item" routerLink="docs" routerLinkActive="active" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-book" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.admin.apidocs">API</span>
                            </a>
                        </li>
                        <!-- jhipster-needle-add-element-to-admin-menu - JHipster will add entities to the admin menu here -->
                    </ul>
                </li>
                <li ngbDropdown class="nav-item dropdown pointer" *ngIf="languages">
                    <a class="nav-link dropdown-toggle" ngbDropdownToggle href="javascript:void(0);" id="languagesnavBarDropdown" *ngIf="languages.length > 1">
                    <span>
                        <i class="fa fa-flag" aria-hidden="true"></i>
                        <span jhiTranslate="global.menu.language">Language</span>
                        <b class="caret"></b>
                    </span>
                    </a>
                    <ul class="dropdown-menu" ngbDropdownMenu *ngIf="languages.length > 1">
                        <li *ngFor="let language of languages">
                            <a class="dropdown-item" [jhiActiveMenu]="language" href="javascript:void(0);" (click)="changeLanguage(language);collapseNavbar();">{{language | findLanguageFromKey}}</a>
                        </li>
                    </ul>
                </li>
                <li ngbDropdown class="nav-item dropdown pointer">
                    <a class="nav-link dropdown-toggle" ngbDropdownToggle href="javascript:void(0);" id="account-menu">
                  <span *ngIf="!getImageUrl()">
                    <i class="fa fa-user" aria-hidden="true"></i>
                    <span *ngIf="!isAuthenticated()" jhiTranslate="global.menu.account.main">
                      Account
                    </span>
                    <b class="caret"></b>
                  </span>
                        <span *ngIf="getImageUrl()">
                      <img [src]="getImageUrl()" class="profile-image img-circle" alt="Avatar">
                  </span>
                        <span *ngIf="isAccount()">
                        {{account.login}}
                    </span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-right" ngbDropdownMenu>
                        <li *ngSwitchCase="true">
                            <a class="dropdown-item" routerLink="settings" routerLinkActive="active" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-wrench" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.account.settings">Settings</span>
                            </a>
                        </li>
                        <li *ngSwitchCase="true">
                            <a class="dropdown-item" routerLink="password" routerLinkActive="active" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-clock-o" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.account.password">Password</span>
                            </a>
                        </li>
                        <li *ngSwitchCase="true">
                            <a class="dropdown-item" (click)="logout()" id="logout">
                                <i class="fa fa-fw fa-sign-out" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.account.logout">Sign out</span>
                            </a>
                        </li>
                        <li *ngSwitchCase="false">
                            <a class="dropdown-item" (click)="login()" id="login">
                                <i class="fa fa-fw fa-sign-in" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.account.login">Sign in</span>
                            </a>
                        </li>
                        <li *ngSwitchCase="false">
                            <a class="dropdown-item" routerLink="register" routerLinkActive="active" (click)="collapseNavbar()">
                                <i class="fa fa-fw fa-registered" aria-hidden="true"></i>
                                <span jhiTranslate="global.menu.account.register">Register</span>
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
