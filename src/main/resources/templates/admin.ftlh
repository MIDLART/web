<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meow</title>
    <style>
        body {
            color: white;
            background-color: #3a3939;
            font-family: Arial, sans-serif;
            font-weight: normal;
            font-size: 20px;
        }

        a {
            text-decoration: none;
        }

        a:visited {
            color: inherit;
        }

        a:link {
            color: inherit;
        }

        header {
            background-color: black;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
        }

        button {
            background-color: white;
            color: black;
            padding: 10px 10px;
            border: 1px solid black;
            border-radius: 5px;
            margin-top: 10px;
            margin-bottom: 10px;
            margin-right: 20px;

            font-size: 20px;
        }

        input[type="submit"] {
            background-color: transparent;
            border: none;
            color: black;
            font-size: 20px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<header>
    <div style="float: left; background-color: black;">
        <h1 style="background-color: black; font-size: 40px;">Meow</h1>
    </div>

    <div style="float: right;">
        <button><a href="/">Главная</a></button>
    </div>
</header>

    <h3>Резервная копия</h3>
    <a href="/admin/backup"><button><span>Скачать</span></button></a>

<form action="admin/backup" method="post" enctype="multipart/form-data">
        <h3>Загрузить резервную копию</h3>
            <label for="backupFile">Файл данных</label>
            <input name="backupFile" id="backupFile" type="file" required>
        <button type="submit">Загрузить</button>
</form>


<h3>Панель администратора</h3>
<table border="1">
    <tr>
        <th>Имя</th>
        <th>Номер телефона</th>
        <th>Активность</th>
        <th>Роли</th>
        <th>Бан</th>
        <th>Редактирование</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.name}</th>
            <th>${user.phoneNumber}</th>
            <th><#if user.active>true<#else>false</#if></th>
            <th><#list user.roles as role>${role}</#list></th>
            <th>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Бан">
                </form>
            </th>
            <th><a href="/admin/user/edit/${user.id}">Редактирование</a></th>
        </tr>
    <#else>
        <h3>Пользователей нет</h3>
    </#list>
</table>
</body>
</html>