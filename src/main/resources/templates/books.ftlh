<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Meow</h1><hr>

    <#if user.email??>
        <h3>Имя пользователя: <i>${user.name}</i></h3>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Выйти"/>
        </form>
        <#if user.isAdmin()>
            <a href="/admin">Панель администратора</a>
        </#if>
    <#else>
        <a href="/login">Войти</a></h1>
    </#if>

    <form action="/" method="get">
        Поиск: <input type="text" name="name"><br>
        <input type="submit" value="Поиск"/>
    </form>

    <#list books as book>
        <div>
            <p>
                <b>${book.name}</b> | <a href="/book/${book.id}">Пу пу пу...</a>
            </p>
        </div>
    </#list>

    <#if user.email??>
        <hr>
        <h3>Добавить бонусы</h3>
        <form action="/book/create" method="post" enctype="multipart/form-data">
            Название объявления: <input type="text" name="name"/><br><br>
            Изображение 1: <input type="file" name="file"/><br><br>
            Изображение 2: <input type="file" name="file2"/><br><br>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Добавить товар"/>
        </form>
    </#if>
</body>
</html>