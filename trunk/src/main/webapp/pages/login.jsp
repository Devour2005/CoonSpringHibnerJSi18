<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<%--<security:authorize
access="isAuthenticated()">
<security:authorize access="hasRole('ROLE_ADMIN')">
<jsp:forward page="/adminPage"/>
</security:authorize>
<security:authorize access="hasRole('ROLE_USER')">
<jsp:forward page="/welcome"/>
</security:authorize>
</security:authorize>--%>

<html>
<head>
    <title>Coon Portal</title>
    <link rel="stylesheet" type="text/css" href="${app}/style.css"/>
    <link rel="shortcut icon" href="${app}pictures/favicon.ico">
    <script type="text/javascript" src="${app}js/script.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://malsup.github.io/jquery.form.js"></script>


    <script type="text/javascript">

      /*  function validateForm() {
            var empName = validate("j_username");
            var empPass = validate("j_password");
            var regLogin = validateLogin();
            if (!empName || !empPass) {
                alert("Name and Password must be filled out");
                return false;
            } else if (!regLogin) {
                alert("Illegal login format! Example 'E-not_2015'");
                return false;
            }else{
                return true;
            }

        }

        function validate(field) {
            var fieldHolder = document.forms["login"][field];
            return fieldHolder && fieldHolder.value;
        }

        function validateLogin() {
            var login = document.forms["login"]["j_username"].value;
            if (!($.trim(login).length > 15)) {
                var logReg = /^([a-zA-Z0-9_-])+$/;
                return logReg.test(login);
            } else {
                return false;
            }
        }*/


    </script>

</head>
<body>


<!--BEGIN of HEADER-->
<DIV id="header">
    <br>
    <br>

    <div align="center">
        <h2>Login, please!</h2>
        Current Locale : ${pageContext.response.locale}

    </div>
    <br>
    <br>
</DIV>
<!-- END of HEADER -->

<!-- BEGIN OF CONTENT-->
<DIV id="content">
    <!--LEFT BLOCK-->
    <DIV class="content_left">
        <div>
            <br>

            <span style="float: right">
                <br>
    <a href="?lang=en">en</a>
    |
    <a href="?lang=ru">ru</a>
</span>
        </div>

        <%--BEGIN OF LOGIN FORM--%>
        <c:url var="secureUrl" value="/j_spring_security_check"/>

        <div class="user_info">
            <div>
                <form name="login"
                      method="POST" onsubmit="return validateForm()"
                      action=" <c:url value="${secureUrl}"/>">

                    <c:if test="${not empty errorMsg}">
                        <div class="error">
                            <c:out value="${errorMsg}"/>
                        </div>
                    </c:if>

                    <br>
                    <%--<strong> Login: </strong>--%>
                    <strong><spring:message code="login.login"/></strong>
                    <%--<input name="j_username" required="required" type="text" size="20"/><br>--%>
                    <input name="j_username" type="text" size="20"/><br>
                    <br> <br>
                    <strong><spring:message code="login.password"/></strong>
                    <%--<strong> Password: </strong>--%>
                    <%--<input name="j_password" required="required" type="password" size="20"/><br>--%>
                    <input name="j_password" type="password" size="20"/><br>
                    <br>
                    <br> <input type="submit" name="submit" value="Login"/>

                </form>
            </div>
            <div>
                <form name="reg" action="${app}/register"
                      method="GET"
                      enctype="application/x-www-form-urlencoded">
                    <input type="submit" name="submit" value="Register"/>
                </form>
            </div>
        </div>
        <%--END OF LOGIN FORM--%>
        <br>


    </DIV>
    <!--END OF LEFT BLOCK-->

    <!--CENTRAL BLOCK-->
    <DIV class="content_center">


        <div class="redaction_block">
            <p class="H3_ERROR"> FOR TESTING </p>

            <form name="listUsers" action="${app}adminPage"
                  method="GET">
                <input type="SUBMIT" name="SUBMIT" value="View All"/>
            </form>
            <br><br>
            <a href="${app}/calculatePage"
               title="calculation">Calculation</a></li>
        </div>

        <br>


    </DIV>
    <!--END OF CENTRAL BLOCK-->

    <!--RIGHT BLOCK-->
    <DIV class="content_right">
        <p align="center"> Advertising</p>
    </DIV>
    <!--END OF RIGHT BLOCK-->

</DIV>
<!-- END OF CONTENT-->

<!-- BEGIN OF FOOTER -->
<DIV id="footer">
    <br>
    ENOT FOOTER
</DIV>
<!-- END OF FOOTER -->


</body>
</html>