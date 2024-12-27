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

    <h1>Информация о читателе</h1>

    <b>Номер телефона: </b> ${reader.phoneNumber}<br>

    <b>Имя: </b> ${reader.name}<br>
    <b>Фамилия: </b> ${reader.surname}<br>
    <b>Отчество: </b> ${reader.patronymic}<br>

    <b>Взять книгу</b><br>
    <form action="/reader/${reader.id}/book_taking" method="post" enctype="multipart/form-data">
        Название книги: <input type="text" name="title" required/><br><br>
        Издание: <input type="number" name="edition" required/><br><br>
        Язык: <input type="text" name="language" required/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button><input type="submit" value="Взять"/></button>
    </form>

    <b>Взятые книги: </b>
    <#list reader.bookTakings as bookT>
        <a href="/book/${bookT.bookCopy.book.id} ">
            ${bookT.bookCopy.book}
        </a>
        <b>Взял: </b>${bookT.startDate}
        <#if bookT.isNull()>
            <b>Вернул: нет</b>
        <button><a href="/reader/${reader.id}/book_taking_delete/${bookT.bookCopy.id}">Вернуть книгу</a></button>
        <#else>
            <b>Вернул: </b>${bookT.endDate}
        </#if>
        <br>

    </#list><br>

</body>
</html>