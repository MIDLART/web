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

    big-button {
        background-color: white;
        color: black;
        padding: 40px 40px;
        border: 1px solid #000000;
        border-radius: 5px;
        margin-top: 50px;
        margin-bottom: 50px;
        margin-right: 50px;
        margin-left: 40px;
        margin-right: 40px;
        position: relative;

        font-size: 32px;
    }

    .user-info {
        float: right;
        background-color: black;
    }

    input[type="submit"] {
        background-color: transparent;
        border: none;
        color: black;
        font-size: 20px;
        cursor: pointer;
    }

    .container {
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 10px;
    }


</style>

</head>
<body>

<header>

    <div style="float: left; background-color: black;">
        <h1 style="background-color: black; font-size: 40px;">Meow</h1>
    </div>

<#if user.name??>

    <div class="user-info">
    <div class="container">
        <div class="box1">
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button><input type="submit" value="Выйти"/></button>
        </form>
        </div>

        <div class="box2">

            <h3 style="background-color: black;">Имя: <i style="background-color: black;">${user.name}</i></h3>
            <h3 style="background-color: black;">Библиотека: <i style="background-color: black;">${user.library.address}</i></h3>

        </div>

    </div>
<#else>
    <button><a href="/login">Войти</a></button>

    </div>
</#if>
</header>


<#if user.name??>
    <div style="clear: both;"></div>
    <div style="float: right;">
        <button><a href="/author">Авторы</a></button><br>
        <button><a href="/genre">Жанры</a></button><br>
        <button><a href="/reader">Читатели</a></button><br>
    <#if user.isAdmin()>
        <button><a href="/library">Библиотеки</a></button><br>
        <button><a href="/admin">Панель администратора</a></button><br>
    </#if>
    </div>

</#if>

<#if user.name??>
    <div style="margin-left: 20px">
    <details>
    <summary><b> Добавить книгу</b></summary><br>
    <form action="/book/create" method="post" enctype="multipart/form-data">
        Название книги: <input type="text" name="title" required/><br><br>
        Издание: <input type="number" name="edition" required/><br><br>
        Язык: <input type="text" name="language" required/><br><br>
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
        <button><input type="submit" value="Добавить книгу"/></button>
    </form>
    </details><br>

        <div style="float: left">
        <form action="/" method="get">
            <div class="container">
                <div class="box1">
                    Поиск: <input type="text" name="searchWord"><br> </div>
                <div class="box2">
                    <button><input type="submit" value="Поиск"/></button> </div>
            </div>
        </form>

        <#list books as book>
            <div>
                <p>
                    <b>${book.title}</b> | <a href="/book/${book.id}">Подробнее</a>
                </p>
            </div>
        </#list>
        </div>
    </div>
</#if>

</body>
</html>