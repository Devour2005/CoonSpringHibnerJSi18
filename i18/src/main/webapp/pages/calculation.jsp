<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.springapp.Calculation.PiCalculator" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Coon Portal</title>
    <link rel="stylesheet" type="text/css" href="${app}/style.css"/>
</head>
<body>

<!--BEGIN of HEADER-->
<DIV id="header">
    <br>
    <br>

    <div align="center">
        <h2>Calculation of Pi</h2>
    </div>
    <br>
</DIV>
<!-- END of HEADER -->
<DIV id="content">

    <!-- BEGIN OF CONTENT-->
    <!--LEFT BLOCK-->
    <DIV class="content_left">
        <br>

        <!--USER INFO BLOCK-->
        <div class="user_info">
            <br>

            <div align="center">
                <h2>User Info!</h2>

                <h3>Welcome, ${user.name}!</h3>

                <h3>Your Login:</h3>

                <h3>${user.login}</h3>

                <h3>Your EMail:</h3>

                <h3>${user.email}</h3>

                <h3>Your were registered: </h3>

                <h3><fmt:formatDate pattern="yyyy-MM-dd" value="${user.regDate}"/></h3>
            </div>
            <div>
                <form name="logout" action="${app}/logout.do"
                      method="POST"
                      enctype="application/x-www-form-urlencoded">
                    <input type="submit" name="submit" value="Logout"/>
                </form>
            </div>
        </div>
        <!--END OF USER INFO BLOCK-->
    </DIV>
    <!--END OF LEFT BLOCK-->

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

        <!--CALCULATION BLOCK-->
        <div class="calculation_block">
            <div>
                <br>

                <%--<form name="calcul" action="${app}/pages/sorry.jsp"--%>
                <sf:form name="calcul" action="${app}/calculation/POST"
                         method="POST"
                         modelAttribute="dataInputForm"
                         enctype="application/x-www-form-urlencoded">

                    <sf:label path="precision"><strong>Enter precision:</strong></sf:label> <br>
                    <sf:input path="precision" type="text" size="20"/><br>
                    <sf:errors path="precision" cssClass="error"/>
                    <br>
                    <sf:label path="numberOfThreads"><strong>Enter number of threads:</strong></sf:label> <br>
                    <sf:input path="numberOfThreads" type="text" size="20"/><br>
                    <sf:errors path="numberOfThreads" cssClass="error"/>
                    <br>
                    <input type="submit" name="submit" value="Calculate"/>
                </sf:form>
            </div>
            <div>
                <form name="cancel" action="${app}/pages/welcome.jsp"
                      method="GET"
                      enctype="application/x-www-form-urlencoded">
                    <input type="SUBMIT" name="SUBMIT" value="Cancel"/>
                </form>
            </div>
        </div>
        <!--END OF CALCULATION BLOCK-->
        <br>


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