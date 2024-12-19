<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meow</title>
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
        <a href="/login">Войти</a>
    </#if>

    <#if user.email??>
        <br><a href="/author">Авторы</a><br>

        <form action="/" method="get">
            Поиск: <input type="text" name="title"><br>
            <input type="submit" value="Поиск"/>
        </form>

        <#list books as book>
            <div>
                <p>
                    <b>${book.title}</b> | <a href="/book/${book.id}">Подробнее</a>
                </p>
            </div>
        </#list>
    </#if>

    <#if user.email??>
        <hr>
        <h3>Добавить книгу</h3>
<#--        <form action="/book/create" method="post" enctype="multipart/form-data">-->
        <form action="/book/create" method="post">
            Название книги: <input type="text" name="title"/><br><br>
            Издание: <input type="number" name="edition"/><br><br>
            Язык: <input type="text" name="language"/><br><br>
<#--            <#list 1..3 as i>-->
<#--                Автор ${i}: <input type="text" name="authors[${i-1}]"/><br><br>-->
<#--            </#list>-->
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Добавить книгу"/>
        </form>
    </#if>

</body>
</html>