<!DOCTYPE html>
<html>
<head>
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

        ul {
            list-style-type: none;
        }

    </style>
</head>
<body>
<header>

    <div style="float: left; background-color: black;">
        <h1 style="background-color: black; font-size: 40px;">Meow</h1>
    </div>
</header>

<div style="text-align: center">
<h4>Регистрация</h4>
<form action="/registration" method="post">
    Имя пользователя: <input type="text" name="name" required><br><br>
    Номер телефона: <input type="text" name="phoneNumber" required><br><br>
    Пароль: <input type="password" name="password" required><br><br>

    <ul>
        <#list libraries as library>
            <li>
                <input type="radio" name="library" value="${library.id}" id="library-${library.id}" required>
                <label for="library-${library.id}">${library.address}</label>
            </li>
        </#list>
    </ul>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button><input type="submit" value="Зарегистрироваться"/></button>
</form>
</div>

<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>

</body>
</html>