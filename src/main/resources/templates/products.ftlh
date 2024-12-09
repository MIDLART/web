<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Meow</h1>
    <form action="/" method="get">
        Поиск: <input type="text" name="name"><br>
        <input type="submit" value="Поиск"/>
    </form>

    <#list products as product>
        <div>
            <p>
                <b>${product.name}</b> | <a href="/product/${product.id}">Пу пу пу...</a>
            </p>
        </div>
    </#list>

    <hr>
    <h3>Добавить бонусы</h3>
    <form action="/product/create" method="post" enctype="multipart/form-data">
        Название объявления: <input type="text" name="name"/><br><br>
        Изображение 1: <input type="file" name="file"/><br><br>
        Изображение 2: <input type="file" name="file2"/><br><br>
        <input type="submit" value="Добавить товар"/>
    </form>
</body>
</html>