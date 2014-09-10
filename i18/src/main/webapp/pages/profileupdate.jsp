<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Coon Portal - Profile Redaction Page</title>
    <link rel="stylesheet" type="text/css" href="${app}/style.css"/>
</head>
<body>
<!--BEGIN of HEADER-->
<DIV id="header">
    <br>
    <br>

    <div align="center">
        <h2>Profile Redaction Page</h2>
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
        <!--NAVIGATION MENU -->
        <div>
            <ul id="navigation">
                <li class="first-link"><a href="${app}/pages/welcome.jsp" title="home">Home</a></li>
                <li><a href="${app}/calculatePage" title="calculation">Calculation</a></li>
                <li><a href="${app}/pages/userprofile.jsp" title="user profile">User profile</a></li>
                <li><a href="${app}/pages/contacts.jsp" title="contacts">Contacts</a></li>
            </ul>
        </div>
        <!--END OF NAVIGATION MENU -->

        <div align="center">
            <br>

            <h2>Enter New Data, please!</h2>
        </div>

        <!--BEGIN OF REDACTION FORM-->
        <DIV class="redaction_block ">
            <div>
                <sf:form name="userUpdate"
                         method="POST"
                         modelAttribute="userForm"
                         action="${app}/edit.do/${user.userId}"
                         enctype="application/x-www-form-urlencoded">

                    <c:if test="${not empty errorMsg}">
                        <div class="error">
                            <c:out value="${errorMsg}"/>
                        </div>
                    </c:if>

                    <sf:label path="login">Enter new login:</sf:label><br>
                    <sf:input path="login" type="text" size="20"/> <br>
                    <sf:errors path="login" cssClass="error"/>
                    <br>
                    <sf:label path="name"><strong>Enter new name:</strong></sf:label> <br>
                    <sf:input path="name" type="text" size="20"/><br>
                    <sf:errors path="name" cssClass="error"/>
                    <br>
                    <sf:label path="password"><strong>Enter new password:</strong></sf:label> <br>
                    <sf:input path="password" type="text" size="20"/><br>
                    <sf:errors path="password" cssClass="error"/>
                    <br>
                    <sf:label path="email"><strong>Enter new Email:</strong></sf:label> <br>
                    <sf:input path="email" type="text" size="20"/><br>
                    <sf:errors path="email" cssClass="error"/>
                    <br>

                    <br> <input type="submit" name="submit" value="Update"/>
                </sf:form>
            </div>

            <form name="cancel" action="${app}/pages/userprofile.jsp"
                  method="GET"
                  enctype="application/x-www-form-urlencoded">
                <input type="SUBMIT" name="SUBMIT" value="Cancel"/>
            </form>
        </DIV>
        <!--END OF REDACTION FORM-->

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