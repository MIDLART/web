<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meow</title>
</head>

<body>
<h1>Информация об Авторе</h1>

<b>Автор: </b>${author.surname} ${author.name} ${author.patronymic}<br>
<b>Книги: </b>
<#list author.books as book>
    <a href="/book/${book.id}">
        ${book.title}
    </a><br>
</#list><br>

</body>
</html>