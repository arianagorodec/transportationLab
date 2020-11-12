<%--
  Created by IntelliJ IDEA.
  User: Ariana
  Date: 27.10.2020
  Time: 18:26
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
    <link href="${contextPath}/resources/bootstrap/css/theme.css" rel="stylesheet">
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
                    <h3>Вы успешно авторизировались!<br> Вы зашли в систему в роли перевозчика.</h3>
                    <p>Данная роль имеет следующие возможности: Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Cras mattis consectetur purus sit amet fermentum.</p>
                    <h3>Личная информация</h3>
                    <div class="card mb-3" style="max-width: 540px;">
                        <div class="row no-gutters">
                            <div class="col-md-4">
                                <img src="${contextPath}/resources/images/images.png" class="card-img" alt="Логистика" style="width: 103%;padding-left: 15px;">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title">${company}</h5>
                                    <p class="card-text">${address}</p>
                                    <p class="card-text">${name}</p>
                                    <p class="card-text">${surname}</p>
                                    <p class="card-text">${mobphone}</p>
                                    <p class="card-text">${email}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <h3>Таблица маршрутов</h3>
                    <table class="table">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Маршрут</th>
                            <th scope="col">Транспорт</th>
                            <th scope="col">Время</th>
                            <th scope="col">Процент</th>
                            <th scope="col">Цена</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${routes}" var = "route" >
                        <tr>
                            <th scope="row">${route.id}</th>
                            <td>${route.cities}</td>
                            <td>${route.transports}</td>
                            <td>${route.time}</td>
                            <td>${route.percent}</td>
                            <td>${route.price}</td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="row">
                        <div class="col-md-6">

                            <div class="btn_s1">
                                <button type="button"  onclick="document.getElementById('id05').style.display='block'">Добавить маршрут</button>
                                <button type="button"><a href="/bag">Калькулятор</a></button>
                                <button type="button"><a href="/logout">Выход</a></button>
                            </div>
                            <div id="id05" class="modal">

                                <form method="post" class="modal-content animate" >
                                    <div class="imgcontainer">
                                        <span onclick="document.getElementById('id05').style.display='none'" class="close" title="Close Modal">×</span>

                                    </div>

                                    <form>
                                        <div class="row">
                                            <h4>Добавление маршрута</h4>
                                            <label for="uname"><b>Маршрут</b></label>
<%--                                            pattern="^[а-яА-ЯеЁa-zA-Z-]+$"--%>
                                            <div class="col-md-6">
                                            <input type="text"  placeholder="Введите точку отправления: Минск" name="city_from" required>
                                            </div>
                                            <div class="col-md-6">
                                            <input type="text"  placeholder="Введите точку прибытия: Москва" name="city_to" required>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label for="uname"><b>Вид транспорта и грузоподъёмность</b></label>
                                            <br>
                                            <div class="col-md-8">
                                                <input type="text" placeholder="Введите грузоподъёмность трансопртного средства в кг" name="weight"  required>
                                            </div>
                                            <div class="col-md-4">
                                                <select name="type_transport">
                                                    <option value="MIXED">Все виды транспорта</option>
                                                    <option value="AUTO">Машина</option>
                                                    <option value="SHIP">Водный транспорт</option>
                                                    <option value=TRAIN">Поезд</option>
                                                    <option value="PLANE">Воздушный транспорт</option>
                                                    <option value="TRUCK">Грузовик</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                            <label for="uname"><b>Дистанция</b></label>
                                            <input type="text" placeholder="Дистанция" name="distance"  pattern="^[ 0-9]+$" required>
                                            </div>
                                            <div class="col-md-6">
                                            <label for="uname"><b>Время</b></label>
                                            <input type="text" placeholder="Введите время доставки в часах" pattern="^[ 0-9]+$" name="time"  required>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                            <label for="uname"><b>Процент</b></label>
                                            <input type="text" placeholder="Введите процент" name="percent"  required>
                                            </div>
                                            <div class="col-md-6">
                                            <label for="uname"><b>Цена</b></label>
                                            <br>
                                            <input type="text" placeholder="Введите цену доставки" pattern="^[ 0-9]+$" name="price"  required>
                                            </div>
                                        </div>
                                        <br>

                                        <div class="row">
                                            <h4>Промежуточный город №1</h4>
                                            <label for="uname"><b>Название города</b></label>
                                            <div class="col-md-6">
                                                <input type="text"  placeholder="Введите название города: Минск" name="dist_city1" required>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label for="uname"><b>Вид транспорта и грузоподъёмность</b></label>
                                            <br>
                                            <div class="col-md-8">
                                                <input type="text" placeholder="Введите грузоподъёмность трансопртного средства в кг" name="weight1"  required>
                                            </div>
                                            <div class="col-md-4">
                                                <select name="type_transport1">
                                                    <option value="MIXED">Все виды транспорта</option>
                                                    <option value="AUTO">Машина</option>
                                                    <option value="SHIP">Водный транспорт</option>
                                                    <option value=TRAIN">Поезд</option>
                                                    <option value="PLANE">Воздушный транспорт</option>
                                                    <option value="TRUCK">Грузовик</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label for="uname"><b>Дистанция</b></label>
                                                <input type="text" placeholder="Дистанция" name="distance1"  pattern="^[ 0-9]+$" required>
                                            </div>
                                            <div class="col-md-6">
                                                <label for="uname"><b>Время</b></label>
                                                <input type="text" placeholder="Введите время доставки в часах" pattern="^[ 0-9]+$" name="time1"  required>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label for="uname"><b>Процент</b></label>
                                                <input type="text" placeholder="Введите процент" name="percent1"  required>
                                            </div>
                                            <div class="col-md-6">
                                                <label for="uname"><b>Цена</b></label>
                                                <br>
                                                <input type="text" placeholder="Введите цену доставки" pattern="^[ 0-9]+$" name="price1"  required>
                                            </div>
                                        </div>
                                        <br>

                                        <div class="row">
                                            <h4>Промежуточный город №2</h4>
                                            <label for="uname"><b>Название города</b></label>
                                            <div class="col-md-6">
                                                <input type="text"  placeholder="Введите название города: Минск" name="dist_city2" required>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label for="uname"><b>Вид транспорта и грузоподъёмность</b></label>
                                            <br>
                                            <div class="col-md-8">
                                                <input type="text" placeholder="Введите грузоподъёмность трансопртного средства в кг" name="weight2"  required>
                                            </div>
                                            <div class="col-md-4">
                                                <select name="type_transport2">
                                                    <option value="MIXED">Все виды транспорта</option>
                                                    <option value="AUTO">Машина</option>
                                                    <option value="SHIP">Водный транспорт</option>
                                                    <option value=TRAIN">Поезд</option>
                                                    <option value="PLANE">Воздушный транспорт</option>
                                                    <option value="TRUCK">Грузовик</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label for="uname"><b>Дистанция</b></label>
                                                <input type="text" placeholder="Дистанция" name="distance2"  pattern="^[ 0-9]+$" required>
                                            </div>
                                            <div class="col-md-6">
                                                <label for="uname"><b>Время</b></label>
                                                <input type="text" placeholder="Введите время доставки в часах" pattern="^[ 0-9]+$" name="time2"  required>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label for="uname"><b>Процент</b></label>
                                                <input type="text" placeholder="Введите процент" name="percent2"  required>
                                            </div>
                                            <div class="col-md-6">
                                                <label for="uname"><b>Цена</b></label>
                                                <br>
                                                <input type="text" placeholder="Введите цену доставки" pattern="^[ 0-9]+$" name="price2"  required>
                                            </div>
                                        </div>
                                        <br>

                                        <button type="submit" style="margin-left: 220px;width: 540px;">Создать заказ </button>
                                    </form>
                                    </label>
                                </form>
                            </div>
                        </div>
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
