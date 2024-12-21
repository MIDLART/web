<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meow</title>
</head>

<body>

<b>Жанр: </b>${genre.name}<br>
<b>Книги: </b>
<#list genre.books as book>
    <a href="/book/${book.id}">
        ${book.title}
    </a><br>
</#list><br>

</body>
</html>