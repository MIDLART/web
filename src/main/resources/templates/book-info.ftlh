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

    <h1>Информация о книге</h1>

    <b>Название: </b> ${book.title}<br>

    <b>Авторы: </b>
    <#list book.authors as author>
        <a href="/author/${author.id} ">
            ${author.name}
            ${author.surname}
            ${author.patronymic}
        </a><br>
    </#list><br>

    <b>Издание: </b> ${book.edition}<br>
    <b>Язык: </b> ${book.language}<br>

    <b>Жанры: </b>
    <#list book.genres as genre>
        <a href="/genre/${genre.id}" >
            ${genre.name}
        </a><br>
    </#list><br>

    <form action="/book/delete/${book.id}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button><input type="submit" value="Удалить книгу"/></button>
    </form>

    <b>Копии книги:</b> ${copy_count}
    <form action="/book_сopy/add/${book.id}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button><input type="submit" value="Добавить копию книги"/></button>
    </form>
    <form action="/book_сopy/delete/${book.id}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button><input type="submit" value="Удалить копию книгу"/></button>
    </form>

</body>
</html>