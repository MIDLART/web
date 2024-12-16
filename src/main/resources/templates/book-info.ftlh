<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>Meow info</h1>

<b>Бонус: </b> ${book.name}<br>

<#list book.images as img>
    <img src="/images/${img.id}" height="60px" alt="img"/><br><br>
</#list>

<b>Название товара: </b>${book.name}<br>
<b>Автор: </b><a href="/user/${book.user.id}">${book.user.name}</a><br>

</body>
</html>