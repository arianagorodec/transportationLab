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
    <!-- Bootstrap core CSS -->
    <link href="${contextPath}/resources/bootstrap/css/theme.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,300,700,100' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Raleway:300,700,900,500' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/typicons/2.0.7/typicons.min.css">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/assets/css/pushy.css">
    <link rel="stylesheet" href="${contextPath}/resources/assets/css/masonry.css">
    <link rel="stylesheet" href="${contextPath}/resources/assets/css/animate.css">
    <link rel="stylesheet" href="${contextPath}/resources/assets/css/magnific-popup.css">
    <link rel="stylesheet" href="${contextPath}/resources/assets/css/odometer-theme-default.css">
    <title>Title</title>
</head>
<body class="">

<section id="contact" class="prefooter wow fadeInUp" data-wow-delay="300ms">
    <div class="container-fluid">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <h3>Здесь можно узнать, какой заказ оптимальный для выполнения!</h3>
                    <p>Используется алгоритм рюкзака</p>
                <form method="post">
                <%--    <input type="text" placeholder="Введите вместимость машины в кг" name="weightC"  required>--%>
                <%--    <input type="text" placeholder="Введите вес грузов в кг через -" name="weightCargo"  required>--%>
                    <select  name="select_route">
                    <c:forEach items="${deals}" var = "deal" >
                        <option value="${deal.city_from}-${deal.city_to}">${deal.city_from}-${deal.city_to}</option>
                    </c:forEach>
                    </select>
                    <button type="submit">Получить результат</button>
                </form>
                <p>${result}</p>
                <p style="display: none">${check}</p>
                <% if (request.getAttribute("check").equals(1)) {%>
                <div>
                    <h3>Таблица найденных маршрутов</h3>
                    <table class="table">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Маршрут</th>
                            <th scope="col">Адрес отправления</th>
                            <th scope="col">Адрес прибытия</th>
                            <th scope="col">Транспорт</th>
                            <th scope="col">Размер груза</th>
                            <th scope="col">Вес груза</th>
                            <th scope="col">Время</th>
                            <th scope="col">Цена</th>
                            <th scope="col">Тип груза</th>
                            <th scope="col">ФИО клиента</th>
                            <th scope="col">Номер телефона клиента</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${allDeals}" var = "clientDeal" >
                            <tr>
                                <td>${clientDeal.id}</td>
                                <td>${clientDeal.distRoutes.route.from}-${clientDeal.distRoutes.cities}-${clientDeal.distRoutes.route.to}</td>
                                <td>${clientDeal.address_from}</td>
                                <td>${clientDeal.address_to}</td>
                                <td>${clientDeal.distRoutes.typeTransportation.type}</td>
                                <td>${clientDeal.size}</td>
                                <td>${clientDeal.weight}</td>
                                <td>${clientDeal.time}</td>
                                <td>${clientDeal.price}</td>
                                <td>${clientDeal.type_goods}</td>
                                <td>${clientDeal.client.surname} ${clientDeal.client.name}</td>
                                <td>${clientDeal.client.mobphone}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <% }%>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
