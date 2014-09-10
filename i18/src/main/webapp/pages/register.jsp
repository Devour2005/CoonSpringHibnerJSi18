<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Coon Portal - Register Page</title>
    <link rel="stylesheet" type="text/css" href="../style.css"/>
</head>
<body>
<!--BEGIN of HEADER-->
<DIV id="header">
    <br>
    <br>

    <div align="center">
        <h2>Registration Page</h2>
    </div>
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
        </div>
        <br>

        <!-- BEGIN OF REGISTRATION FORM-->
        <DIV class="registration_block">
            <div>
                <sf:form name="regData"
                         method="POST"
                         modelAttribute="newUser"
                         action="${app}register.do"
                         enctype="application/x-www-form-urlencoded">

                    <c:if test="${not empty errorMsg}">
                        <div class="error">
                            <c:out value="${errorMsg}"/>
                        </div>
                    </c:if>
                    <br>

                    <sf:label path="login"><strong>Enter Login:</strong></sf:label>
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