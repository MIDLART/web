<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>Meow info</h1>

<b>Бонус: </b> ${product.name}<br>

<#list product.images as img>
    <img src="/images/${img.id}" height="60px" alt="img"/><br><br>
</#list>

<b>Название товара: </b>${product.name}<br>
<b>Автор: </b>${product.user.name}<br>
<b>Номер телефона: </b>${product.user.phoneNumber}<br>

</body>
</html>