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

<hr>
<h3>Убрать бонусы</h3>
<form action="/product/delete/${product.id}" method="post">
    <input type="submit" value="Убрать добрую штучку">
</form>
</body>
</html>