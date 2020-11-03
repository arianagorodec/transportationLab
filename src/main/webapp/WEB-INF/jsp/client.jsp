<%--
  Created by IntelliJ IDEA.
  User: Ariana
  Date: 27.10.2020
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>УЦП</title>
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
    <script>
        window.odometerOptions = {
            selector: '.odometer',
            format: '(,ddd)',
            duration: 13000,
            theme: 'default'
        };
    </script>
</head>
<body class="">

<section id="contact" class="prefooter wow fadeInUp" data-wow-delay="300ms">
    <div class="container-fluid">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <h3>Вы успешно авторизировались!<br> Вы зашли в систему в роли клиента.</h3>
                    <p>Данная роль имеет следующие возможности: Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Cras mattis consectetur purus sit amet fermentum.</p>
                    <h3>Личная информация</h3>
                    <div class="card mb-3" style="max-width: 540px;">
                        <div class="row no-gutters">
                            <div class="col-md-4">
                                <img src="${contextPath}/resources/images/images1.png" class="card-img" alt="Логистика" style="width: 40%; padding-left: 17px;">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body" style="padding-top: 30px;">
                                    <p class="card-text">${name}</p>
                                    <p class="card-text">${surname}</p>
                                    <p class="card-text">${mobphone}</p>
                                    <p class="card-text">${email}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${deals!=null}">
                    <h3>Таблица заказов</h3>
                    <table class="table">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Компания</th>
                            <th scope="col">Маршрут</th>
                            <th scope="col">Транспорт</th>
                            <th scope="col">Время</th>
                            <th scope="col">Цена</th>
                        </tr>
                        </thead>
                        <tbody
                        <c:forEach items="${deals}" var = "deal" >
                        <tr>
                            <th scope="row"></th>
                            <td>${deal.distRoutes.route.partner.company}</td>
                            <td>${deal.distRoutes.cities}</td>
                            <td>${deal.distRoutes.typeTransportation.type}</td>
                            <td>${deal.distRoutes.typeTransportation.time}</td>
                            <td>${deal.price}</td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </c:if>
                    <c:if test="${deals==null}">
                        <p>У Вас нет заказов</p>
                    </c:if>
                    <div class="row">
                            <div class="btn_s1">
                                <button type="button"  onclick="document.getElementById('id05').style.display='block'">Добавить заказ</button>
                                <button type="button"><a href="/logout">Выход</a></button>
                            </div>
                            <div id="id05" class="modal">

                                <form class="modal-content animate" id="client-form" action="/findRoute"  method="post">
                                    <div class="imgcontainer">
                                        <span onclick="document.getElementById('id05').style.display='none'" class="close" title="Close Modal">×</span>
                                    </div>
                                    <form >
                                        <h4 style="padding-left: 45px;">Добавление заказа</h4>
                                        <div class="row">
                                            <div class="col-md-5">
                                                <label for="uname"><b>Город отправки</b></label>
                                                <br>
                                                <select name="city_from">
                                                    <c:forEach items="${citiesFrom}" var = "cityFrom" >
                                                    <option value="${cityFrom}">${cityFrom}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-md-5">
                                                <label for="uname"><b>Город прибытия</b></label>
                                                <br>
                                                <select name="city_to">
                                                    <c:forEach items="${citiesTo}" var = "cityTo" >
                                                        <option value="${cityTo}">${cityTo}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label for="uname"><b>Адрес отправления</b></label>
<%--                                            pattern="^[а-яА-ЯеЁa-zA-Z-]+$"--%>
                                            <input type="text" placeholder="Страна-Город-Улица-Дом-№квартиры(если есть)"  name="address_from"  required>
                                        </div>
                                        <div class="row">
                                            <label for="uname"><b>Адрес доставки</b></label>
                                            <input type="text" placeholder="Страна-Город-Улица-Дом-№квартиры(если есть)"  name="address_to"  required>
                                        </div>
                                        <div class="row">
                                            <label for="transport"><b>Вид транспорта</b></label>
                                            <select name="type_transport">
                                                <option value="MIXED">Все виды транспорта</option>
                                                <option value="AUTO">Машина</option>
                                                <option value="SHIP">Водный транспорт</option>
                                                <option value="TRAIN">Поезд</option>
                                                <option value="TRUCK">Грузовик</option>
                                                <option value="PLANE">Воздушный транспорт</option>
                                            </select>
                                        </div>
                                        <div class="row">
                                            <label for="uname"><b>Размеры груза</b></label>
                                            <br>
                                            <div class="col-md-4">
                                                <input type="text" placeholder="Введите объем груза в метрах кубических" pattern="^[ 0-9]+$" name="size"  required>
                                            </div>
                                            <br>
                                            <label for="weight"><b>Вес</b></label>
                                            <input type="text" placeholder="Введите вес груза в кг" pattern="^[ 0-9]+$" name="weight"  required>
                                        </div>
                                        <div class="row">
                                            <label for="uname"><b>Тип груза</b></label>
                                            <select name="type_goods">
                                                <option value="NOTHING">Ничего</option>
                                                <option value="CRUMBLY">Рассыпчатые грузы</option>
                                                <option value="DANDEROUS">Опасные грузы</option>
                                                <option value="HEATMODE">Необходим теплорежим</option>
                                                <option value="FRAGILE">Хрупкий груз</option>
                                            </select>
                                        </div>
                                        <br>
                                        <button type="submit"  style="margin-left: 220px;width: 540px;" >Создать маршрут</button>
                                    </form>
                                </form>
                            </div>
<%--                                Вывод существующих маршрутов  в бд--%>
                        <p style="display: none">${check}</p>
                        <% if (request.getAttribute("check").equals(1)) {%>
                                <div>
                                    <h3>Таблица найденных маршрутов</h3>
                                    <table class="table">
                                        <thead class="thead-light">
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Компания</th>
                                            <th scope="col">Маршрут</th>
                                            <th scope="col">Адрес отправления</th>
                                            <th scope="col">Адрес прибытия</th>
                                            <th scope="col">Транспорт</th>
                                            <th scope="col">Размер груза</th>
                                            <th scope="col">Вес груза</th>
                                            <th scope="col">Время</th>
                                            <th scope="col">Цена</th>
                                            <th scope="col">Тип груза</th>
                                            <th scope="col"></th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        <c:forEach items="${newDeals}" var = "newDeal" >
                                            <form method="post" action="/addDeal">
                                                <input type="number" name="size" style="display: none" value="${myDeal.size}" readonly>
                                                <input type="number" name="weight" style="display: none" value="${myDeal.weight}" readonly>
                                                <input type="text" name="type_goods" style="display: none" value="${myDeal.type_goods}" readonly>
                                                <input type="text" name="address_from" style="display: none" value="${myDeal.address_from}" readonly>
                                                <input type="text" name="address_to" style="display: none" value="${myDeal.address_to}" readonly>
                                                <tr>
                                                    <th scope="row"><input type="number" name="id" style="outline: none; border: none; background: #f9f9f9;" value="${newDeal.distRoutes.id}" readonly></th>
                                                    <td>${newDeal.distRoutes.route.partner.company}</td>
                                                    <td>${newDeal.distRoutes.cities}</td>
                                                    <td>${newDeal.address_from}</td>
                                                    <td>${newDeal.address_to}</td>
                                                    <td>${newDeal.distRoutes.typeTransportation.type}</td>
                                                    <td>${newDeal.size}</td>
                                                    <td>${newDeal.weight}</td>
                                                    <td>${newDeal.time}</td>
                                                    <td>${newDeal.price}</td>
                                                    <td>${newDeal.type_goods}</td>
                                                    <td><button class="selected" type="submit">Заказать</button>
                                                </tr>
                                            </form>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                        <% }%>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>

<script src="${contextPath}/resources/assets/js/jquery.min.js"></script>
<script src="${contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.0.4/js/bootstrap-scrollspy.js"></script>
<script src="${contextPath}/resources/assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="http://masonry.desandro.com/masonry.pkgd.js"></script>
<script src="${contextPath}/resources/assets/js/masonry.js"></script>
<script src="${contextPath}/resources/assets/js/pushy.min.js"></script>
<script src="${contextPath}/resources/assets/js/jquery.magnific-popup.min.js"></script>
<script src="${contextPath}/resources/assets/js/wow.min.js"></script>
<script src="${contextPath}/resources/assets/js/scripts.js"></script>
<script src="${contextPath}/resources/assets/js/odometer.js"></script>
<script>
    var modal = document.getElementById('id01');
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>
