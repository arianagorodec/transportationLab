<%--
  Created by IntelliJ IDEA.
  User: Ariana
  Date: 18.10.2020
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="utf-8">
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
    <link rel="stylesheet" href="${contextPath}/resources/assets/css/magnific-popup.css">
    <link rel="stylesheet" href="${contextPath}/resources/assets/css/odometer-theme-default.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/maskedinput.js"></script>
    <script>
        window.odometerOptions = {
            selector: '.odometer',
            format: '(,ddd)',
            duration: 13000,
            theme: 'default'
        };
    </script>

    <script type="text/javascript">
        jQuery(function($){
            $("#phone").mask("+375(99) 999-9999");
        });
    </script>
    <script type="text/javascript">
        jQuery(function($){
            $("#cphone").mask("+375(99) 999-9999");
        });
    </script>
</head>
<body class="">
<!-- Site Overlay -->
<sec:authorize access="hasRole('ROLE_PARTNER')">
    <% response.sendRedirect("partner"); %>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_USER')">
    <% response.sendRedirect("client"); %>
</sec:authorize>
<div class="site-overlay"></div>
<header id="home">
    <div class="container-fluid">
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-xs-10">
                    <a href="#" class="thumbnail logo">
                        <img src="${contextPath}/resources/images/your_logo.png" alt="" class="img-responsive">
                    </a>
                </div>
                <div class="col-md-1 col-md-offset-8 col-xs-2 text-center">
                    <div class="menu-btn"><span class="hamburger">&#9776;</span></div>
                </div>
            </div>
            <div class="jumbotron">
                <h1><small>Будьте всегда первыми</small></br>
                    <strong>Autolight Express</strong></h1>
                <p>Вы попали на сайт организации международных грузоперевозак.</p>
                <p>
                    <button onclick="document.getElementById('id01').style.display='block'" class="btn btn-primary btn-lg" style="width:auto;">Login</button>
                    <button onclick="document.getElementById('id02').style.display='block'" class="btn btn-primary btn-lg" style="width:auto;">Registration</button>
                <div id="id01" class="modal">

                    <form method="post" class="modal-content animate" >
                        <div class="imgcontainer">
                            <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">×</span>
                            <img src="${contextPath}/resources/images/avatar.png" alt="Avatar" class="avatar">
                        </div>

                        <div class="container">
                            <label for="uname"><b>Логин(почта)</b></label>
                            <input type="text" placeholder="Введите логин" name="username" required>

                            <label for="psw"><b>Пароль</b></label>
                            <input type="password" placeholder="Введите пароль" name="password" required>

                            <button  type="submit" >Login</button>
                            <label>
                                <input type="checkbox" checked="checked" name="remember"> Remember me  <span class="psw">Forgot <a href="#">password?</a></span>
                            </label>
                        </div>
                    </form>
                </div>


                <div id="id02" class="modal">
                    <form class="modal-content animate" >
                        <div class="imgcontainer">
                            <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">×</span>
                        </div>

                        <div class="container">
                            <label for="uname"><b>Registration</b></label>
                            <button  type="button" onclick="document.getElementById('id03').style.display='block'">Registration Partner</button>
                            <button  type="button" onclick="document.getElementById('id04').style.display='block'">Registration Client</button>
                        </div>
                    </form>
                </div>

                <div id="id03" class="modal">
                        <form:form method="POST" modelAttribute="partnerForm" class="modal-content animate" action="/partner_registration">
<%--                            <form class="modal-content animate" action="/partner_registration">--%>
                        <div class="imgcontainer">
                            <span onclick="document.getElementById('id03').style.display='none'" class="close" title="Close Modal">×</span>
                            <img src="${contextPath}/resources/images/avatar.png" alt="Avatar" class="avatar">
                        </div>
                        <div class="container">
                            <label for="company"><b>Наименование компании</b></label><br>
                            <form:input type="text" path="company" cssStyle="width: 300px;" id="company" name="cname" placeholder="Название компании"></form:input><br>
                            <form:errors path="company"></form:errors>
                            <p style="color: red">${companyError}</p>
                            <label for="address"><b>Адрес компании</b></label><br>
                            <form:input type="text" path="address" cssStyle="width: 300px;" class="form-control" id="address" name="caddres" placeholder="Адрес компании"></form:input><br>
                            <form:errors path="address"></form:errors>
                            <p style="color: red">${addressError}</p>
                            <label for="first-name"><b>Имя представителя</b></label><br>
                            <form:input type="text" path="name" cssStyle="width: 300px;" id="first-name" name="uname" placeholder="Имя"></form:input><br>
                            <form:errors path="name"></form:errors>
                            <p style="color: red">${nameError}</p>
                            <label for="last-name"><b>Фамилия представителя</b></label><br>
                            <form:input type="text" path="surname"  cssStyle="width: 300px;" id="last-name" name="usurname" placeholder="Фамилия" ></form:input><br>
                            <form:errors path="surname"></form:errors>
                            <p style="color: red">${surnameError}</p>
                            <label for="uname"><b>Пол</b></label><br>
                            <form:radiobutton path="gender" id="gender-male" name="gender" value="male"/>
                            <label for="gender-male">Мужсой</label>
                            <form:radiobutton path="gender" id="gender-female" name="gender" value="female" />
                            <label for="gender-female">Женский</label><br>
                            <form:errors path="gender"></form:errors>
                            <p style="color: red">${genderError}</p>
                            <label for="phone"><b>Мобильный телефон</b></label><br>
                            <form:input type="text" path="mobphone" cssStyle="width: 300px;" id="phone" name="phone" placeholder="+375 (29)-999-7777" ></form:input><br>
                            <form:errors path="mobphone"></form:errors>
                            <p style="color: red">${mobphoneError}</p>
                            <label for="your_email"><b>Email(Логин)</b></label><br>
                            <form:input type="text" name="email" cssStyle="width: 300px;" id="your_email"  pattern="[^@]+@[^@]+.[a-zA-Z]{2,6}" path="username" placeholder="example@email.com"
                                        autofocus="true"></form:input><br>
                            <form:errors path="username"></form:errors>
                            <p style="color: red">${usernameError}</p>
                            <label for="password-input"><b>Пароль</b></label><br>
                            <form:input  type="password" cssStyle="width: 300px;" id="password-input" path="password"  placeholder="Введите пароль" name="psw"></form:input><br>
                            <form:errors path="password"></form:errors>
                            <p style="color: red">${passwordError}</p>
                            <button type="submit" >Зарегистировать</button>
                            <a href="index.jsp">Отмена</a>
                            <label>
                                <input type="checkbox" checked="checked" name="remember"> Remember me
                            </label>
                        </div>
                        </form:form>
<%--                    </form>--%>
                </div>


                <div id="id04" class="modal">
<%--                    <form class="modal-content animate" action="/action_page.php">--%>
                    <form:form method="POST" modelAttribute="clientForm" class="modal-content animate" action="/client_registration">

                        <div class="imgcontainer">
                            <img src="${contextPath}/resources/images/avatar.png" alt="Avatar" class="avatar">
                        </div>
                        <div class="container">
                            <label for="first-name"><b>Имя</b></label>
                            <form:input type="text" path="name" class="form-control" id="first-name" name="first-name" placeholder="Имя"></form:input>
                            <form:errors path="name"></form:errors>
                            <p style="color: red">${nameError}</p>
                            <label for="last-name"><b>Фамилия</b></label>
                            <form:input type="text" path="surname" class="form-control" id="last-name" name="last-name" placeholder="Фамилия" ></form:input>
                            <form:errors path="surname"></form:errors>
                            <p style="color: red">${surnameError}</p>
                            <label for="uname"><b>Пол</b></label>
                            <form:radiobutton path="gender" id="gender-male" name="gender" value="male"/>
                            <label for="gender-male">Мужсой</label>
                            <form:radiobutton path="gender" id="gender-female" name="gender" value="female" />
                            <label for="gender-female">Женский</label>
                            <form:errors path="gender"></form:errors>
                            <p style="color: red">${genderError}</p>
                            <label for="phone"><b>Телефон</b></label>
                            <form:input type="text" path="mobphone" class="form-control" id="phone" name="phone" placeholder="+375 (29)-999-7777" ></form:input>
                            <form:errors path="mobphone"></form:errors>
                            <p style="color: red">${mobphoneError}</p>
                            <label for="your_email"><b>Email(Логин)</b></label>
                            <form:input type="text" name="your_email" id="your_email" class="form-control" pattern="[^@]+@[^@]+.[a-zA-Z]{2,6}" path="username" placeholder="example@email.com"
                                        autofocus="true"></form:input>
                            <form:errors path="username"></form:errors>
                            <p style="color: red">${usernameError}</p>
                            <label for="password-input"><b>Пароль</b></label>
                            <form:input  type="password" id="password-input" path="password"  placeholder="Введите пароль" name="password"></form:input>
                            <form:errors path="password"></form:errors>
                            <p style="color: red">${passwordError}</p>
                            <button type="submit">Зарегистировать</button>
                            <a href="index.jsp">Отмена</a>
                            <label>
                                <input type="checkbox" checked="checked" name="remember"> Remember me
                            </label>
                        </div>
                    </form:form>
<%--                    </form>--%>
                </div>
            </div>
        </div>
    </div>
</header>

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


