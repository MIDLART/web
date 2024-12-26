<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meow</title>
</head>
<body>
    <h1>Meow</h1><hr>

    <br><a href="/">Главная</a><br>

    <form action="/reader" method="get">
        Поиск: <input type="text" name="searchWord"><br>
        <input type="submit" value="Поиск"/>
    </form>

    <#list readers as reader>
        <div>
            <p>
                <b>${reader.surname} ${reader.name} ${reader.patronymic}</b>
                <br> <b>${reader.phoneNumber}</b>
                    | <a href="/reader/${reader.id}">Подробнее</a>
            </p>
        </div>
    </#list>

    <hr>
    <h3>Добавить читателя</h3>
    <form action="/reader/create" method="post" enctype="multipart/form-data">
        Номер телефона: <input type="text" name="phoneNumber"/><br><br>
        Имя: <input type="text" name="name"/><br><br>
        Фамилия: <input type="text" name="surname"/><br><br>
        Отчество: <input type="text" name="patronymic"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить автора"/>
    </form>

</body>
</html>