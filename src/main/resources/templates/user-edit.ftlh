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

<h3>Редактирование пользователя ${user.name}</h3>
<form action="/admin/user/edit" method="post">
    <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
        </div>
    </#list>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Сохранить</button>
</form>
</body>
</html>