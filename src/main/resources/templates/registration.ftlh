<!DOCTYPE html>
<html>
<head>
    <title>Meow</title>
</head>
<body>
<h1>Meow</h1><hr>

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
    <input type="submit" value="Зарегистрироваться"/>
</form>

<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>

</body>
</html>