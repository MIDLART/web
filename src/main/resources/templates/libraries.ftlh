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

<#--    <form action="/library" method="get">-->
<#--        Поиск: <input type="text" name="address"><br>-->
<#--        <input type="submit" value="Поиск"/>-->
<#--    </form>-->


<br>
<div style="margin-left: 20px">
    <details>
        <summary><b>Добавить библиотеку</b></summary>
    <form action="/library/create" method="post" enctype="multipart/form-data">
        Адрес: <input type="text" name="address"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button><input type="submit" value="Добавить библиотеку"/></button>
    </form>
        </details>

    <b>Список библиотек:</b>
    <#list libraries as library>
        <div>
            <p>
<#--                <b>${library.address}</b> | <a href="/library/${library.id}">Подробнее</a>-->
                <b>${library.address}</b>
            </p>
        </div>
    </#list>

    </div>

</body>
</html>