<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Meow</title>
</head>

<body>
    <h1>Информация о читателе</h1>

    <b>Номер телефона: </b> ${reader.phoneNumber}<br>

    <b>Имя: </b> ${reader.name}<br>
    <b>Фамилия: </b> ${reader.surname}<br>
    <b>Отчество: </b> ${reader.patronymic}<br>

    <br><a href="/reader/${1}/book_taking/save/${1}">Го</a><br>

    <b>Взятые книги: </b>
    <#list reader.bookTakings as bookT>
        <a href="/book/${bookT.bookCopy.book.id} ">
            ${bookT.bookCopy.book}<br>
        </a><br>
        <b>Взял: </b>${bookT.startDate}
        <b>Вернул: </b>${bookT.endDate}
    </#list><br>

</body>
</html>