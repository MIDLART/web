<!DOCTYPE html>
<html>
<head>
    <title>Meow</title>
</head>
<body>
<h1>Meow</h1><hr>
<h3>Подробная информация о пользователе ${user.name}</h3>
<b>Номер телефона: </b> ${user.phoneNumber}<br>

<#--<#list books as book>-->
<#--    <div>-->
<#--        <p><b>${book.name}</b> | <a href="/book/${book.id}">Подробнее...</a></p>-->
<#--    </div>-->
<#--<#else>-->
<#--    <h3>нет</h3>-->
<#--</#list>-->
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>