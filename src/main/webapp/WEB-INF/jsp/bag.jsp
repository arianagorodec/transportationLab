<%--
  Created by IntelliJ IDEA.
  User: Ariana
  Date: 29.10.2020
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <input type="text" placeholder="Введите вместимость машины в кг" name="weightC"  required>
    <input type="text" placeholder="Введите вес грузов в кг через -" name="weightCargo"  required>
    <button type="submit">Получить результат</button>
</form>
<p>Вмещается ${result} кг груза</p>
</body>
</html>
