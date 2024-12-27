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
    </style>
</head>
<body>
<header>

    <div style="float: left; background-color: black;">
        <h1 style="background-color: black; font-size: 40px;">Meow</h1>
    </div>
</header>


<div style="text-align: center">
<h4 >Авторизация</h4>
<form action="/login" method="post">
    Имя: <input type="text" name="username"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button><input type="submit" value="Вход"/></button>
</form>
<br><button><a href="/registration">Зарегистрироваться</a></button>
</div>

</body>
</html>