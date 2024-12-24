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
        <a href="/author/${author.id} ">
            ${author.name}
            ${author.surname}
            ${author.patronymic}
        </a><br>
    </#list><br>

    <b>Издание: </b> ${book.edition}<br>
    <b>Язык: </b> ${book.language}<br>

    <b>Жанры: </b>
    <#list book.genres as genre>
        <a href="/genre/${genre.id}" >
            ${genre.name}
        </a><br>
    </#list><br>

    <b>Id: </b> ${book.id}

    <form action="/book/delete/${book.id}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Удалить книгу"/>
    </form>

    <b>Копии книги:</b> ${copy_count}
    <form action="/book_сopy/add/${book.id}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Добавить копию книги"/>
    </form>
    <form action="/book_сopy/delete/${book.id}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Удалить копию книгу"/>
    </form>

</body>
</html>