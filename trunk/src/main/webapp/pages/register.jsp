<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<c:set var="css" value="${pageContext.request.contextPath}/style.css"/>
<html>
<head>
    <title>Coon Portal - Register Page</title>
    <link rel="stylesheet" type="text/css" href="${css}"/>
    <link rel="shortcut icon" href="${app}/pictures/favicon.ico">
    <script type="text/javascript" src="${app}js/script.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://malsup.github.io/jquery.form.js"></script>


    <script type="text/javascript">

      /*  function validateForm() {
            var empName = isEmpty("name");
            var empLogin = isEmpty("login");
            var empPass = isEmpty("password");
            var empPassConfirm = isEmpty("passwordConfirm");
            var empEmail = isEmpty("email");
            var empEmailConfirm = isEmpty("emailConfirm");
            var regLogin = validateLogin("login");
            var regEmail = validateEmail("email");
            var regEmailConfirm = validateEmail("emailConfirm");
            var regName = validateName("name");
            var validatePass = validatePassword();
            if (!empName || !empLogin || !empPass || !empPassConfirm || !empEmail || !empEmailConfirm) {
                alert("All fields must be filled out");
                return false;
            } else if (!regLogin) {
                alert("Illegal login format! Example 'E-not_2015'");
                return false;
            } else if (!validatePass) {
                alert("Passwords do not match!");
                return false;
            } else if (!regEmail || !regEmailConfirm) {
                alert("Illegal email format! Example 'XYZ_W@gmail.com'");
                return false;
            } else if (!regEmail === regEmailConfirm) {
                alert("Emails do not match!");
                return false;
            } else if (!regName) {
                alert("Illegal Username format! Username must have alphabet characters only!");
                return false;
            } else {
                return true;
            }
        }

        function isEmpty(field) {
            var fieldHolder = document.forms["regData"][field];
            return fieldHolder && fieldHolder.value;
        }

        function validateLogin() {
            var login = document.forms["regData"]["login"].value;
            if (!($.trim(login).length > 15)) {
                var logReg = /^([a-zA-Z0-9_-])+$/;
                return logReg.test(login);
            } else {
                return false;
            }
        }

        function validateEmail() {
            var email = document.forms["regData"]["email"].value;
            var chrbeforAt = email.substr(0, email.indexOf('@'));
            if (!($.trim(email).length > 127)) {
                if (chrbeforAt.length >= 2) {
                    var regExp = /^([a-zA-Z0-9_\.\-+])+@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                    return regExp.test(email);
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }

        function validateName() {
            var name = document.forms["regData"]["name"].value;
            if (!($.trim(name).length > 25)) {
                var nameReg = /^([a-zA-Z])+$/;
                return nameReg.test(name);
            } else {
                return false;
            }
        }

        function validatePassword() {
            var pass = document.forms["regData"]["password"].value;
            var passConfirm = document.forms["regData"]["passwordConfirm"].value;
            return pass === passConfirm;
        }
*/
    </script>


</head>
<body>
<!--BEGIN of HEADER-->
<DIV id="header">
    <br>
    <br>

    <div align="center">
        <h2>Registration Page</h2>
    </div>
    <span style="float: right">
                <br>
    <a href="?lang=en">en</a>
    |
    <a href="?lang=ru">ru</a>
    </span>

    Current Locale : ${pageContext.response.locale}
    <br>
</DIV>
<!-- END of HEADER -->

<!-- BEGIN OF CONTENT-->
<DIV id="content">

    <DIV class="content_left">  <!-- BEGIN OF CONTENT-->
    </DIV>

    <!--CENTRAL BLOCK-->
    <DIV class="content_center">
        <div align="center">
            <h2>Register, please!</h2>
            <br>

        </div>
        <br>

        <!-- BEGIN OF REGISTRATION FORM-->
        <DIV class="registration_block">
            <div>
                <sf:form name="regData"
                         method="POST" onsubmit="return validateRegForm()"
                         modelAttribute="newUser"
                         action="${app}register.do"
                         enctype="application/x-www-form-urlencoded">

                    <c:if test="${not empty errorMsg}">
                        <div class="error">
                            <c:out value="${errorMsg}"/>
                        </div>
                    </c:if>
                    <br>

                    <%--<sf:label path="login"><strong>Enter Login:</strong></sf:label>--%>
                    <sf:label path="login"><strong><spring:message code="register.enterLogin"
                                                                   text="default text"/></strong></sf:label>
                    <sf:input path="login" type="text" size="20"/><br>
                    <sf:errors path="login" cssClass="error"/>
                    <%--<span class="error"><sf:errors path="login"/></span>--%>
                    <br>

                    <sf:label path="name"><strong>Enter Name:</strong></sf:label>
                    <sf:input path="name" type="text" size="20"/><br>
                    <sf:errors path="name" cssClass="error"/>
                    <%--<span class="error"><form:errors path="name"/></span>--%>
                    <br>

                    <sf:label path="password"><strong>Enter Password:</strong></sf:label>
                    <sf:input path="password" type="text" size="20"/><br>
                    <sf:errors path="password" cssClass="error"/>
                    <%--<span class="error"><form:errors path="password"/></span>--%>
                    <br>

                    <sf:label path="passwordConfirm"><strong>Confirm Password:</strong></sf:label>
                    <sf:input path="passwordConfirm" type="text" size="20"/><br>
                    <sf:errors path="passwordConfirm" cssClass="error"/>
                    <%--<span class="error"><form:errors path="passwordConfirm"/></span>--%>
                    <br>

                    <sf:label path="email"><strong>Enter Email:</strong></sf:label>
                    <sf:input path="email" type="text" size="20"/><br>
                    <sf:errors path="email" cssClass="error"/>
                    <%--<span class="error"><form:errors path="email"/></span>--%>
                    <br>

                    <sf:label path="emailConfirm"><strong>Confirm Email:</strong></sf:label>
                    <sf:input path="emailConfirm" type="text" size="20"/><br>
                    <sf:errors path="emailConfirm" cssClass="error"/>
                    <%--<span class="error"><form:errors path="emailConfirm"/></span>--%>
                    <br>

                    <sf:label path="role">Role</sf:label>
                    <sf:select path="role">
                        <sf:option value="user" label="user"/>
                        <sf:option value="admin" label="admin"/>
                    </sf:select>
                    <br> <br>

                    <input type="SUBMIT" name="SUBMIT" value="Register"/>
                </sf:form>

                <form name="cancel" action="${app}/login"
                      method="GET"
                      enctype="application/x-www-form-urlencoded">
                    <input type="SUBMIT" name="SUBMIT" value="Cancel"/>
                </form>

            </div>
        </DIV>
        <!-- END OF REGISTRATION FORM-->

    </DIV>
    <!--END OF CENTRAL BLOCK-->

    <!--RIGHT BLOCK-->
    <DIV class="content_right">
        <p align="center"> Advertising</p>
    </DIV>
    <!--END OF RIGHT BLOCK-->
</DIV>
<!-- END of CONTENT-->

<!-- BEGIN of FOOTER -->
<DIV id="footer">
    <br>
    ENOT FOOTER
</DIV>
<!-- END of FOOTER -->


</body>
</html>