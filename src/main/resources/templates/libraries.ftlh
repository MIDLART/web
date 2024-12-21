<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meow</title>
</head>
<body>
    <h1>Meow</h1><hr>

    <br><a href="/">Главная</a><br>

<#--    <form action="/library" method="get">-->
<#--        Поиск: <input type="text" name="address"><br>-->
<#--        <input type="submit" value="Поиск"/>-->
<#--    </form>-->

    <#list libraries as library>
        <div>
            <p>
<#--                <b>${library.address}</b> | <a href="/library/${library.id}">Подробнее</a>-->
                <b>${library.address}</b>
            </p>
        </div>
    </#list>

    <hr>
    <h3>Добавить библиотеку</h3>
    <form action="/library/create" method="post" enctype="multipart/form-data">
        Адрес: <input type="text" name="address"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить жанр"/>
    </form>

</body>
</html>