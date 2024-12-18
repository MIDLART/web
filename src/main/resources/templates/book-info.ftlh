<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meow</title>
</head>

<body>
<h1>Информация о книге</h1>

<b>Название: </b> ${book.title}<br>
<b>Авторы: </b>
    <#list book.authors as author>
        <a href="/author/${author.id}">
        ${author.name}
        ${author.surname}
        ${author.patronymic}
        </a><br>
    </#list><br>
<b>Издание: </b> ${book.edition}<br>
<b>Язык: </b> ${book.language}<br>

</body>
</html>