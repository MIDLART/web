<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meow</title>
</head>
<body>
<h1>Meow</h1><hr>

<br><a href="/">Главная</a><br>

<form action="/author" method="get">
    Поиск: <input type="text" name="searchWord"><br>
    <input type="submit" value="Поиск"/>
</form>

<#list authors as author>
    <div>
        <p>
            <b>${author.surname} ${author.name} ${author.patronymic}</b>
                | <a href="/author/${author.id}">Подробнее</a>
        </p>
    </div>
</#list>

    <hr>
    <h3>Добавить автора</h3>
    <form action="/author/create" method="post" enctype="multipart/form-data">
        Имя: <input type="text" name="name"/><br><br>
        Фамилия: <input type="text" name="surname"/><br><br>
        Отчество: <input type="text" name="patronymic"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить автора"/>
    </form>

</body>
</html>