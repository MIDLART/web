<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meow</title>
</head>
<body>
    <h1>Meow</h1><hr>

    <br><a href="/">Главная</a><br>

    <form action="/genre" method="get">
        Поиск: <input type="text" name="name"><br>
        <input type="submit" value="Поиск"/>
    </form>

    <#list genres as genre>
        <div>
            <p>
                <b>${genre.name}</b> | <a href="/genre/${genre.id}">Подробнее</a>
            </p>
        </div>
    </#list>

    <hr>
    <h3>Добавить жанр</h3>
    <form action="/genre/create" method="post" enctype="multipart/form-data">
        Название жанра: <input type="text" name="name"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить жанр"/>
    </form>

</body>
</html>