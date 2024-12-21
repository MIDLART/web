<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meow</title>
</head>
<body>
    <h1>Meow</h1><hr>

    <#if user.name??>
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

    <#if user.name??>
        <br><a href="/author">Авторы</a><br>
        <br><a href="/genre">Жанры</a><br>

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

    <#if user.name??>
        <hr>
        <h3>Добавить книгу</h3>
        <form action="/book/create" method="post" enctype="multipart/form-data">
<#--        <form action="/book/create" method="post">-->
            Название книги: <input type="text" name="title"/><br><br>
            Издание: <input type="number" name="edition"/><br><br>
            Язык: <input type="text" name="language"/><br><br>
<#--            <#list 1..3 as i>-->
<#--                Автор ${i}: <input type="text" name="authors[${i-1}]"/><br><br>-->
<#--            </#list>-->
            Авторы:
            <ul>
                <#list authors as author>
                    <li>
                        <input type="checkbox" name="authors" value="${author.id}" id="author-${author.id}">
                        <label for="author-${author.id}">${author.surname} ${author.name} ${author.patronymic}</label>
                    </li>
                </#list>
            </ul>

            Жанры:
            <ul>
                <#list genres as genre>
                    <li>
                        <input type="checkbox" name="genres" value="${genre.id}" id="genre-${genre.id}">
                        <label for="genre-${genre.id}">${genre.name}</label>
                    </li>
                </#list>
            </ul>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Добавить книгу"/>
        </form>
    </#if>

</body>
</html>