<html>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>Pupil home</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="/scripts/languageChanger.js"></script>
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
    <h2></h2>
</div>
<div id="content">
    <table>
        <tr>
            <th colspan="5">My schedule</th>
        </tr>
        <tr>
            <th>Position</th>
            <th>Lesson</th>
            <th>Homework</th>
            <th>Classroom</th>
            <th>Teacher</th>
        </tr>
    <#list model["mySchedule"] as schedule>
        <tr>
            <td>${schedule.lessonPosition}</td>
            <td>${schedule.lessonName}</td>
            <td>${schedule.homework}</td>
            <td>${schedule.classroomName}</td>
            <td>${schedule.teacherFirstName} ${schedule.teacherLastName}</td>
        </tr>
    </#list>
    </table>
</div>
</body>
</html>
