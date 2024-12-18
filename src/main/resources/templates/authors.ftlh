<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meow</title>
</head>
<body>
<h1>Meow</h1><hr>

<form action="/author" method="get">
    Поиск: <input type="text" name="surname"><br>
    <input type="submit" value="Поиск"/>
</form>

<#list authors as author>
    <div>
        <p>
            <b>${author.surname} ${author.name} ${author.patronymic}</b>
                | <a href="/author/${author.id}">Пу пу пу...</a>
        </p>
    </div>
</#list>

<#if user.email??>
    <hr>
    <h3>Добавить автора</h3>
    <form action="/author/create" method="post" enctype="multipart/form-data">
        Имя: <input type="text" name="name"/><br><br>
        Фамилия: <input type="text" name="surname"/><br><br>
        Отчество: <input type="text" name="patronymic"/><br><br>
        <input type="submit" value="Добавить автора"/>
    </form>
</#if>
</body>
</html>